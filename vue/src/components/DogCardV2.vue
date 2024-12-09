<template>
  <div class="container">
    <div class="card">
      <img v-bind:src="this.currentDog.img" class="card-img-top"/>
      <div class="card-body">
        <h2 class="card-title">{{ this.currentDog.dogName }}</h2>
      </div>
      <ul class="card-text">
        <li> Breed: {{this.currentDog.breed?.breedName}}</li>
        <li>breedId: {{this.currentDog.breedId}}</li>
        <li> Age: {{this.currentDog.age}}</li>
        <li> Size: {{this.currentDog.size}}</li>
        <li> Gender: {{this.currentDog.gender}}</li>
        <li> Agency: {{this.currentDog.agencyId}}</li>
<!--        <li v-for="trait in this.currentDog.breed.traits" v-bind:key="trait.traitId">Traits: {{trait.trait.id}}</li>-->
      </ul>
    </div>
  </div>

</template>

<script>
import DogService from "@/services/DogService";
import BreedService from "@/services/BreedService";

export default {
  props: {
    currentDogList: {}
  },
  data() {
    return {
      currentDog: this.currentDogList,


    }
  },
  created() {
    this.getDogBreed();
  },
  methods: {

    getDogBreed() {
     BreedService.getBreedById(this.currentDog.breedId).then(response => {
        this.currentDog.breed = response.data;
      }
     )
    },


  }

}

</script>

<style scoped>

</style>