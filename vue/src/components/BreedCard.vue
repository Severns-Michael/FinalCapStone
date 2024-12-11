<template>
  <div class="container">

    <div class="card">
      <div class="polaroid-image-container">
        <img v-bind:src="this.swipedBreed.img" class="card-img-top"/>
      </div>
      <div class="polaroid-caption">
        <h2 class="card-title">{{ this.breed.officialName }}</h2>
        <ul class="card-text">
          <li v-for="trait in this.breed.traits" v-bind:key="trait.traitId"> {{ trait.traitName }}</li>
        </ul>
      </div>
      
    </div>
  </div>
</template>

<script>
import BreedService from '../services/BreedService';
import DogService from '../services/DogService';


export default {
  props: {
    swipedBreed: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      currSwipedBreed: {
        userId: this.$store.state.user.id,
      },
      breed: {}
    }
  },
  beforeMount() {
    this.initializeSwipedBreed();
  },
  methods: {
    getDogPic() {
      if (!this.breed.subBreed === null) {
        DogService.getSubBreedPic(this.breed.breedName, this.breed.subBreed).then(response => {
          this.currSwipedBreed.img = response.data.message
        });
      } else {
        DogService.getBreedPic(this.breed.breedName).then(response => {
          this.currSwipedBreed.img = response.data.message
        });
      }
    },
    initializeSwipedBreed() {
      if (this.swipedBreed && this.swipedBreed.breedId) {
        console.log('this is running');
        this.currSwipedBreed.breedId = this.swipedBreed.breedId;
        this.currSwipedBreed.img = this.swipedBreed.img;
        BreedService.getBreedById(this.swipedBreed.breedId).then(response => {
          this.breed = response.data
        });
        if (this.currSwipedBreed.img === null) {
          this.getDogPic();
        }
      } else {
        console.warn('Breed data is not ready yet');
      }
    }

  },
  watch: {
    breed: {
      handler(newVal) {
        if (newVal && newVal.breedId) {
          this.initializeSwipedBreed();
        }
      },
      immediate: true
    }
  }
}
</script>

<style scoped>

img {
  max-width: 300px;
}

.container {
  max-width: 300px;
}

.polaroid-image-container {
  width: 100%;
  height: 300px;
  overflow: hidden;
  background-color: #f0f0f0;
  border-radius: 8px;

}

.polaroid-image-container img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.polaroid-caption {
  margin-top: 10px;
  text-align: center;
}

.polaroid-caption h2 {
  margin: 0 0 10px;
  font-size: 1.2em;
}

.polaroid-caption ul {
  list-style: none;
  padding: 0;
  margin: 0;
  font-size: 0.9em;
  color: #333;
}

</style>