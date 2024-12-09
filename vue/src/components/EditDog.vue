<template>
  <form class="breed">
    <div class="breed-selector">
      <label>Breed: </label>
      <select v-model="this.selectedBreed.officialName" @change="getSelectedBreed" v-on:click="getBreeds">
        <option v-for="breed in this.breeds" v-bind:key="breed.breedName"> {{ breed.officialName }}</option>
      </select>
    </div>
  </form>
</template>
<script>
import DogService from "@/services/DogService";
import BreedService from "@/services/BreedService";
export default {

}

</script>

<style scoped>

</style>