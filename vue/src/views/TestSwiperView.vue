<template>
  <test-swipe-card v-bind:breed=this.breed></test-swipe-card>
</template>

<script>
import TestSwipeCard from '../components/TestSwipeCard.vue';
import BreedService from '../services/BreedService';


export default {
  data() {
    return {
      randomBreeds: [],
      breed: {}
    }
  },
  components: {
    TestSwipeCard
  },
  created() {
    BreedService.getRandomBreeds().then(response => {
      this.randomBreeds = response.data
      this.getNextBreed();
    });
  },
  methods: {
    getNextBreed() {
      this.breed = this.randomBreeds.pop()
    }
  }
}
</script>

<style scoped>

</style>