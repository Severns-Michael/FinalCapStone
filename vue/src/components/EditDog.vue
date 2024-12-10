<template>
  <form class="breed">
    <div class="breed-selector">
      <label>Dog: </label>
      <select v-model="selectedDog.dogName" @change="getSelectedDog">
        <option v-for="dog in dogs" v-bind:key="dog.dogId">{{ dog.dogName }}</option>
      </select>
    </div>

    <div>
      <label for="Breed">Breed: </label>
      <select v-model="this.selectedDog.breedId" @change="getSelectedDog">
        <option v-for="breed in breeds" v-bind:key="breed.breedId" v-bind="this.selectedDog">
          {{ breed.officialName }}
        </option>
      </select>
    </div>

    <div>
      <label>Agency: </label>
      <select v-model="this.selectedDog.agencyId" @change="getSelectedDog">
        <option v-for="agency in agenciesList" :key="agency.agencyId" :value="agency.agencyId">
          {{ agency.agencyName }}
        </option>
      </select>
    </div>

    <div>
        <label for="age">Age: </label>
        <input id="age" type="number" v-model="selectedDog.age" />
    </div>

    <div>
      <label for="image-url">Image Url: </label>
      <input id="image-url" type="text" v-model="selectedDog.img" />

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

    <div>
      <h6>Gender: </h6>
      <input id="Male" type="radio" value="Male" v-model="selectedDog.gender" />
      <label for=""> Male </label>

      <input id="Female" type="radio" value="Female" v-model="selectedDog.gender" />
      <label for="Female"> Female </label>
    </div>

    <div>
      <button type="submit" class="btn btn-light">Submit</button>
    </div>
  </form>
</template>

<script>
import DogService from "../services/DogService";
import BreedService from "../services/BreedService";
export default {
  data() {
    return {
      dogs: [],
      selectedDog: {
        size: ""
      },
      agency: {},
      agenciesList: [],
    };
  },
  created() {
    DogService.getAllDogs().then(response => {
      if (response.status === 200) {
        this.dogs = response.data;
      }
    });
    DogService.getAllAgencies().then(response => {
      if (response.status === 200) {
        this.agenciesList = response.data;
      }
    });
    BreedService.getBreeds().then(response => {
      if (response.status === 200) {
        this.breeds = response.data;
      }
    });


  },
  methods: {
    getSelectedDog() {
      // Logic to handle selected dog change, if needed
    },
    getAllAgencies(){
      DogService.getAllAgencies().then(response => {
        if (response.status === 200) {
          this.dogs = response.data;
        }
      });
    },
    getAgencyById() {
      DogService.getAgencyById(this.selectedDog.agencyId).then(response => {
        if (response.status === 200) {
          this.agency = response.data;
        }
      });
    },
    getBreedById() {
      BreedService.getBreedById(this.selectedDog.breedId).then(response => {
        if (response.status === 200) {
          this.breed = response.data;
        }
      });
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
