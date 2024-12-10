<template>
  <div class="fh-box fh-row" id="breed-swiper">


    <breed-swiper/>


  </div>
</template>

<script>
import SwipeCard from '../components/SwipeCard.vue';
import BreedService from '../services/BreedService';
import TestSwipeCard from "../components/TestSwipeCard.vue";
import BreedSwiper from "../components/BreedSwiper.vue";
// import BreedSwiper from "@/components/BreedSwiper.vue";


export default {
  data() {
    return {
      randomBreeds: [],
      breed: {}
    }
  },
  components: {
    BreedSwiper,
    // SwipeCard
    // TestSwipeCard
    // BreedSwiper
    // BreedCard
    // SwipeCard
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