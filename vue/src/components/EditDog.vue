<template>
  <form class="admin-box" id="edit-dog" v-on:submit.prevent="this.updateDog()">
    <div class="breed-selector">
      <label>Dog : </label>
      <select v-model="this.selectedDog.dogName" @change="this.getSelectedDog()" v-on:click="this.getAllDogs();">
        <option v-for="dog in this.alphabetizedDogs" v-bind:key="dog.dogId">{{ dog.dogName }}</option>
      </select>
    </div>

    <div>
      <label for="Breed">Breed : </label>
      <select v-model="this.selectedDogBreed.officialName">
        <option v-for="breed in breeds" v-bind:key="breed.breedId" v-bind="this.selectedDog">
          {{ breed.officialName }}
        </option>
      </select>
    </div>

    <div>
      <label>Agency : </label>
      <select v-model="this.selectedDog.agencyId">
        <option v-for="agency in this.alphabetizedAgencies" :key="agency.agencyId" :value="agency.agencyId">
          {{ agency.agencyName }}
        </option>
      </select>
    </div>

    <div>
        <label for="age">Age : </label>
        <input id="age" type="number" v-model="selectedDog.age" min = "0"/>
    </div>

    <div>
      <label for="image-url">Image Url : </label>
      <input id="image-url" type="text" v-model="selectedDog.img" />

    </div>

    <div>
      <h6>Size: </h6>
      <input id="small" type="radio" value="1" v-model="selectedDog.size" />
      <label for="small"> Small </label>

      <input id="medium" type="radio" value="2" v-model="selectedDog.size" />
      <label for="medium"> Medium </label>

      <input id="large" type="radio" value="3" v-model="selectedDog.size" />
      <label for="large"> Large </label>
    </div>

    <div>
      <h6>Gender: </h6>
      <input id="Male" type="radio" value="0" v-model="selectedDog.gender" />
      <label for=""> Male </label>

      <input id="Female" type="radio" value="1" v-model="selectedDog.gender" />
      <label for="Female"> Female </label>
    </div>

    <div>
      <button type="submit" class="btn purple-btn">Submit</button>
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
      selectedDog: {},
      selectedDogBreed: {},
      agency: {},
      agenciesList: []
    };
  },
  created() {
    this.getAllDogs();
    this.getAllAgencies();
    BreedService.getBreeds().then(response => {
      if (response.status === 200) {
        this.breeds = response.data;
      }
    });
  },
  computed: {
    alphabetizedAgencies() {
      const alphaAgencies = this.agenciesList;
      alphaAgencies.sort((a, b) => {
        const nameA = a.agencyName.toUpperCase(); 
        const nameB = b.agencyName.toUpperCase(); 
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
          return 0;
      });
      return alphaAgencies;
    },
    alphabetizedDogs() {
      const alphaDogs = this.dogs;
      alphaDogs.sort((a, b) => {
        const nameA = a.dogName.toUpperCase(); 
        const nameB = b.dogName.toUpperCase(); 
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
          return 0;
      });
      return alphaDogs;
    }
  },
  methods: {
    getSelectedDog() {
      this.dogs.find(dog => {
        if (this.selectedDog.dogName === dog.dogName) {
          this.selectedDog = dog;
        }
      });
      this.getBreedById();
    },
    getAllAgencies(){
      DogService.getAllAgencies().then(response => {
        if (response.status === 200) {
          this.agenciesList = response.data;
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
          this.selectedDogBreed = response.data;
        }
      });
    },
    updateDog() {
      DogService.updateDog(this.selectedDog).then(response => {
        if (response.status === 200) {
          this.selectedDog = {
            dogName: '',
          },
          this.selectedDogBreed = {}
        }
      });
    },
    getAllDogs() {
      DogService.getAllDogs().then(response => {
        if (response.status === 200) {
          this.dogs = response.data;
        }
      });
    }

  }
};
</script>

<style scoped>

form#edit-dog {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
  max-width: 500px;
  background-color: var(--purp5);
  border: 1px solid var(--purp1);
  border-radius: 15px;
  padding: 20px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
  color: white;
  margin: 20px auto;
  box-sizing: border-box;
}
form#edit-dog label {
  font-weight: bold;
  margin-bottom: 5px;
  color: white;
}
form#edit-dog input,
form#edit-dog select {
  width: 60%; 
  padding: 8px 10px;
  margin-bottom: 15px;
  border: 1px solid var(--purp3);
  border-radius: 5px;
  background-color: white;
  color: var(--purp1);
  font-size: 1em;
  box-sizing: border-box;
}
form#edit-dog input[type="radio"] {
  width: auto; 
  margin-right: 5px;
  height: auto;
}
form#edit-dog h6 {
  margin-top: 15px;
  font-size: 1.1em;
  color: white;
}
form#edit-dog button.purple-btn {
  align-self: center; 
  width: auto; 
  margin-top: 15px;
}
form#edit-dog div {
  display: flex;
  flex-direction: row;
  align-items: center; 
  justify-content: space-between; 
  width: 100%; 
  margin-bottom: 15px;
}
label {
  margin: 5px;
}
input {
  margin: 5px;
}
.purple-btn:hover {
    background-color: var(--purp5);
    color: white;
  } 
  .purple-btn {
  padding: 8px 16px; 
  font-size: 0.9em; 
  color: white;
  background-color: var(--purp1);
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%; 
  max-width: 200px; 
  text-align: center;
  box-sizing: border-box; 
}

input[type="radio"] {
  width: 40px;
  height: 40px;
  accent-color: mediumslateblue; /* Change radio button color */
}
</style>
