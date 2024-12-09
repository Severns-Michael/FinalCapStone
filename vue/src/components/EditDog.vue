<template>
  <form class="breed">
    <div class="breed-selector">
      <label>Dog: </label>
      <select v-model="this.dogs.dogName" @change="getSelectedDog" v-on:click="getAllDogs">
        <option v-for="dog in this.dogs" v-bind:key="dog.dogId"> {{ dog.dogName }}</option>
      </select>
    </div>
    <div>
        <label for="age">Age: </label>
        <input id="age" type="number" v-model="this.selectedDog.age" />
    </div>
    <div>
      <h6>Size: </h6>
      <input id="small" type="radio" />
      <label for="small"> Small </label>
    </div>
  </form>
</template>

<script>
import DogService from "@/services/DogService";
export default {
  data() {
    return {
      dogs: [],
      selectedDog: {}
    }
  },
  created() {
    DogService.getAllDogs().then(response => {
      if (response.status === 200) {
        this.dogs = response.data;
      }
    })
  },
  methods: {

  }
}

</script>

<style scoped>

</style>