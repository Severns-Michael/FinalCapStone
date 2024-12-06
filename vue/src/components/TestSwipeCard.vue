<template>
  <div
      class="card">
    <img :src="swipedBreed.img" class="card-img-top" />
    <div class="card-body">
      <h2 class="card-title">{{ breed.officialName }}</h2>
    </div>
    <ul class="card-text">
      <li v-for="trait in breed.traits" :key="trait.traitId">{{ trait.traitName }}</li>
    </ul>
  </div>
</template>

<script>
import DogService from '../services/DogService';
import TestSwiperView from '../views/SwipingView.vue';


export default {
  props: {
    breed: {}
  },
  data() {
    return {
      swipedBreed: {}

    }
  },
  beforeMount() {
    this.initializeSwipedBreed();
  },
  methods: {
    getDogPic() {
      if (!this.breed.subBreed === null) {
        DogService.getSubBreedPic(this.breed.breedName, this.breed.subBreed).then(response => {
          this.swipedBreed.img = response.data.message
        });
      } else {
        DogService.getBreedPic(this.breed.breedName).then(response => {
          this.swipedBreed.img = response.data.message
        });
      }
    },
    addToSwipedBreeds(value) {
      this.swipedBreed.yes = value;
      DogService.addToSwipedBreeds(this.swipedBreed).then(response => {
        if (response.status === 201) {
          this.swipedBreed = {};
        }
      });
      TestSwiperView.methods.getNextBreed();
    },
    initializeSwipedBreed() {
      this.swipedBreed.breedId = this.breed.breedId;
      this.getDogPic();
    }

  }
}
</script>

<style scoped>

</style>