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
      <input id="small" type="radio" v-on:click="selectRadio(1)" v-bind:class="{selected: isSmall}"/>
      <label for="small"> Small </label>

      <input id="medium" type="radio" v-on:click="selectRadio(2)" v-bind:class="{selected: isMedium}"/>
      <label for="medium"> Medium </label>

      <input id="large" type="radio" v-on:click="selectRadio(3)" v-bind:class="{selected: isLarge}"/>
      <label for="large"> Large </label>
    </div>
  </form>
</template>

<script>
import DogService from "@/services/DogService";
export default {
  data() {
    return {
      dogs: [],
      selectedDog: {},
      isSmall: false,
      isMedium: false,
      isLarge: false
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
    selectRadio(value) {
      this.selectedDog.size = value;
      if (value === 1) {
        this.isSmall = true;
      } 
      if (value === 2) {
        this.isMedium = true;
      } 
      if (value === 3) {
        this.isLarge = true;
      } 
    }
  }
}

</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  align-items: center;
}


</style>