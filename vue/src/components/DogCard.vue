<template>
    <div class="container">

      <div class="card">
        <img v-bind:src="this.currentDog.img" class="card-img-top"/>
        <div class="card-body">
          <h2 class="card-title">{{ this.currentDog.dogName }}</h2>
        </div>
        <ul class="card-text">
            <li v-for="trait in this.currentDogBreed.traits" v-bind:key="trait.traitId"> {{ trait.traitName }} </li>
        </ul>
        <div class="card-swipe">
        <a href="" v-text="`I love ${this.currentDog.dogName}!`" class="btn btn-primary"></a>
        <a href="" v-text="'Not for me!'" class="btn btn-danger"></a>
        </div>
      </div>
    </div>
</template>

<script>
import DogService from '../services/DogService';
import BreedService from '../services/BreedService';

export default {
    props: {
        swipedBreed: {}
    },
    data() {
        return {
            currentSwipedBreed: this.swipedBreed,
            currentDogBreed: {},
        }
    },
    beforeMount() {
        this.getDogBreed();
    },
    methods: {
        getDogBreed() {
            BreedService.getBreedById(this.currentSwipedBreed.breedId).then(response => {
                this.currentDogBreed = response.data;
                this.getDogPic();
            });
        },
        getDogPic() {
            if (this.currentSwipedBreed.img === null) {
                if (!this.currentDogBreed.subBreed === null) {
                    DogService.getSubBreedPic(this.currentDogBreed.breedName, this.currentDogBreed.subBreed).then(response => {
                        this.currentSwipedBreed.img = response.data.message
                    });
                } else {
                    DogService.getBreedPic(this.currentDogBreed.breedName).then(response => {
                        this.currentSwipedBreed.img = response.data.message
                    });
                }
            }
        }
    }
}
</script>

<style scoped>
:root {
  --purp1: #4B0365 !important;
  --purp2: #600581 !important;
  --purp3: #7c08a6 !important;
  --purp4: #8a2cac !important;
  --purp5: #a04ebd !important;
}
    .card-swipe{
      display: flex;
      flex-direction: row;
      justify-content: space-around;
      margin: 30px;
    }
    .card{
      border: var(--purp3) solid 2px;
      max-height: 50vh;
      max-width: 35vh;
    }
    .card-img-top{
      max-height: 30vh;
    }
    user-dashboard{
      display: flex;
      flex-direction: column;

    }
</style>