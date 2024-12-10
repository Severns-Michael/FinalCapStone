<template>
  <div class="container">
    <div class="card">
      <img v-bind:src="this.currentDog.img" class="card-img-top"/>
      <div class="card-body">
        <h2 class="card-title">{{ this.currentDog.dogName }}</h2>
      </div>
      <ul class="card-text">
        <li> Breed: {{this.currentDog.breed?.officialName}}</li>
        <li> Age: {{this.currentDog.age}} years old</li>
        <li> Size: {{this.getDogSize()}}</li>
        <li> Gender: {{this.getDogGender()}}</li>
        <li> Agency: {{this.getDogAgencyName()}}</li>
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
    getDogSize() {
      if (this.currentDog.size === 1) {
        return 'Small'
      }
      if (this.currentDog.size === 2) {
        return 'Medium'
      }
      if (this.currentDog.size === 3) {
        return 'Large'
      }
    },
    getDogGender() {
      if (this.currentDog.gender === 0) {
        return 'Male'
      }
      if (this.currentDog.gender === 1) {
        return 'Female'
      }
    },
    getDogAgencyName() {
      DogService.getAgencyById(this.currentDog.agencyId).then(response => {
        let agency = response.data;
        return agency.agencyName
      });
    }
  }

}

</script>

<style scoped>

</style>