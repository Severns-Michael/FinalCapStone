<template>
  <form class="breed">
    <div class="breed-selector">
      <label>Dog: </label>
      <select v-model="selectedDog.dogName" @change="getSelectedDog">
        <option v-for="dog in dogs" v-bind:key="dog.dogId">{{ dog.dogName }}</option>
      </select>
    </div>
    <div>
        <label for="age">Age: </label>
        <input id="age" type="number" v-model="selectedDog.age" />
    </div>
    <div>
      <h6>Size: </h6>
      <input id="small" type="radio" value="small" v-model="selectedDog.size" />
      <label for="small"> Small </label>

      <input id="medium" type="radio" value="medium" v-model="selectedDog.size" />
      <label for="medium"> Medium </label>

      <input id="large" type="radio" value="large" v-model="selectedDog.size" />
      <label for="large"> Large </label>
    </div>
  </form>
</template>

<script>
import DogService from "../services/DogService";
export default {
  data() {
    return {
      dogs: [],
      selectedDog: {
        size: ""
      }
    };
  },
  created() {
    DogService.getAllDogs().then(response => {
      if (response.status === 200) {
        this.dogs = response.data;
      }
    });
  },
  methods: {
    getSelectedDog() {
      // Logic to handle selected dog change, if needed
    }
  }
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
