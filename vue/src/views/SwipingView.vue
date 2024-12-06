<template>
  <div class="container">
    <div class="swiper">

      <div class="swipe swipe-yes"></div>

      <div class="current-dog">
        <swipe-card v-bind:breed=this.breed></swipe-card>
      </div>

      <div class="swipe swipe-no"></div>

    </div>


  </div>
</template>

<script>
import SwipeCard from '../components/SwipeCard.vue';
import BreedService from '../services/BreedService';

export default {
  data() {
    return {
      randomBreeds: [],
      breed: {}
    }
  },
  components: {
    SwipeCard
  },
  created() {
    BreedService.getRandomBreeds().then(response => {
      console.log("initialize Random Breed")
      this.randomBreeds = response.data
      this.getNextBreed();
    });
  },
  methods: {
    getNextBreed() {
      console.log(this.randomBreeds);
      this.breed = this.randomBreeds.pop();
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>