<template>
  <div class="swipe" ondragover="hoverHandler(event)" @drop="swipeYes($event,this.currentBreed)" id="yes-div">
   yes div
  </div>

  <div class="swipe swipe-dog" id="dog-div">

    <!--    <div class="card drag-card" draggable="true" id="drag-card" ondragstart="pickupHandler(event)"-->
    <!--         ondragover="hoverHandler(event)" ondrop="dropHandler(event)">-->
    <!--      <img draggable="false" class="card-img-top" :src="this.currentBreed.imgpath"/>-->
    <!--      <div class="card-body">-->
    <!--        <h2 v-text="this.currentBreed.officialName"></h2>-->
    <!--        <ul>-->
    <!--          <li v-for="trait in this.currentBreed.traits" v-text="trait.traitName"></li>-->
    <!--        </ul>-->
    <!--      </div>-->
    <!--    </div>-->
    <swipe-card :swipedBreed="this.swipedBreed" :currentBreed="this.currentBreed">
      <div class="loading"></div>
    </swipe-card>
  </div>
  <div class="swipe" ondragover="hoverHandler(event)" @drop="swipeNo($event,this.currentBreed)" id="no-div">
    no div
  </div>


</template>

<script>
import BreedService from "@/services/BreedService";
import DogService from "../services/DogService";
import SwipeCard from "@/components/SwipeCard.vue";

export default {
  beforeMount() {
    this.getRandomBreedList();
  },
  mounted() {

  },
  data() {
    return {
      swipedBreed: {
        userId: this.$store.state.user.id
      },
      currentBreed: {},
      randomBreedList: [],
    }
  },
  methods: {
    setCurrentBreedImage() {
      if (!this.currentBreed.subBreed) {
        DogService.getBreedPic(this.currentBreed.breedName).then(
            response => {
              this.swipedBreed.img = response.data.message;
            }
        );
      } else if(this.currentBreed.subBreed) {
        DogService.getSubBreedPic(this.currentBreed.breedName, this.currentBreed.subBreed).then(
            response => {
              this.swipedBreed.img = response.data.message;
            }
        )
      }
    },
    getRandomBreedList() {
      BreedService.getRandomBreeds().then(response => {
        this.randomBreedList = response.data;
        BreedService.getBreedById(this.randomBreedList.pop().breedId).then(
            response => {
              this.currentBreed = response.data
              this.swipedBreed.breedId = this.currentBreed.breedId;
              this.setCurrentBreedImage();
            }
        );
      });
    },
    getNextBreed() {
      BreedService.getBreedById(this.randomBreedList.pop().breedId).then(
          response => {
            this.currentBreed = response.data
            this.swipedBreed.breedId = this.currentBreed.breedId;
            this.setCurrentBreedImage();
          }
      );
      console.log('getNextBreed()')
    },
    swipeYes(e, breed) {
      console.log('you swiped YES');
      dropHandler(e);
      this.swipedBreed = {...this.swipedBreed, breedId: breed.breedId};
      this.swipedBreed.swipeYes = true;
      console.log(this.swipedBreed);
      DogService.addToSwipedBreeds(this.swipedBreed).then(response => {
        console.log('addToSwipedBreeds().then()')
        if (response.status === 201) {
          this.swipedBreed = {
            userId: this.$store.state.user.id
          };
          this.currentBreed={};
          this.getNextBreed()
          resetCardPos();
        }
      });
    },
    swipeNo(e, breed) {
      console.log('you swiped NO');
      dropHandler(e);
      this.swipedBreed = {...this.swipedBreed, breedId: breed.breedId};
      this.swipedBreed.swipeYes = false;
      console.log(this.swipedBreed);
      DogService.addToSwipedBreeds(this.swipedBreed).then(response => {
        console.log('addToSwipedBreeds().then()')
        if (response.status === 201) {
          this.swipedBreed = {
            userId: this.$store.state.user.id
          };
          this.currentBreed={};
          this.getNextBreed()
          resetCardPos();
        }
      });
    },
  },
  components: {
    SwipeCard
  },


}

</script>

<style>

.container {
  display: flex;
  justify-content: center;
  align-items: center;
}
.loading {
  height: 120px;
  width: 120px;
  border: 5px solid;
  border-color: white transparent white transparent;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg)
  }
}

</style>