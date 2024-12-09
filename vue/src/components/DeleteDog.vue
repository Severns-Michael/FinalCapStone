<template>
  <div class="container">
    <div>
      <label>Breed: </label>
      <select v-model="this.selectedDog" v-on:click="getDogs()">
        <option v-for="dog in dogs" v-bind:key="dog.dogId">
          {{ dog.dogName }}
        </option>
      </select>
    </div>
    <button v-on:click.prevent="this.deleteDog()" class="btn btn-light">Delete Dog</button>
  </div>
</template>

<script>
import DogService from "@/services/DogService";

export default {
  data() {
    return {
      selectedDog: {},
      dogs: [],
    }
  },
  created() {
    DogService.getAllDogs().then((response) => {
      this.dogs = response.data;
    })
  },
  methods: {
    deleteDog() {
      this.dog.find(dog => {
        if (dog.dogName === this.selectedDog.dogName) {
          this.selectedDog = dog;
        }
      });
      DogService.deleteBreed(this.selectedDog.breedId).then(response => {
        this.selectedDog = {};
        DogService.getAllDogs().then(response => {
          this.dogs = response.data;
        });
      }).catch(error => {
        console.log(error);
      });
    }
  }
}
</script>

<style scoped>

</style>