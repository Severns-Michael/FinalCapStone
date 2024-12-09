<template>
  <div class="fh-box">
    <div class="swipe-yes">yes</div>

    <div class="card-to-swipe">
      <breed-card :breed="this.breed"/>
    </div>

    <div class="swipe-no">no</div>
  </div>
</template>
<script>
import breedCard from "@/components/BreedCard.vue";
import BreedService from "@/services/BreedService";
export default {
  data(){
    return {
      randomBreeds: [],
      breed: {}
    }
  },
  created() {
    BreedService.getRandomBreeds().then(response => {
      console.log("initialize Random Breed")
      this.randomBreeds = response.data
      this.getNextBreed();
    });
  },
  components:{
  breedCard
  },
  methods: {
    getNextBreed() {
      console.log(this.randomBreeds);
      this.breed = this.randomBreeds.pop();
    }
  }
}
</script>
<style>
</style>