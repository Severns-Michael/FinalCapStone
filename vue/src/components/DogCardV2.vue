<template>
  <div class="polaroid-card">
    
    <div class="polaroid-image-container">
      <img v-bind:src="this.currentDog.img" alt="Dog photo" />
    </div>


    <div class="polaroid-caption">
      <h2>{{ this.currentDog.dogName }}</h2>
      <ul>
        <li>Breed: {{ this.currentDog.breed?.officialName }}</li>
        <li>Age: {{ this.currentDog.age }} years old</li>
        <li>Size: {{ this.getDogSize() }}</li>
        <li>Gender: {{ this.getDogGender() }}</li>
        <li>Agency: {{ this.currentAgency.agencyName }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import DogService from "../services/DogService";
import BreedService from "../services/BreedService";

export default {
  props: {
    currentDogList: {}
  },
  data() {
    return {
      currentDog: this.currentDogList,
      agencies: [],
      currentAgency: {}
    }
  },
  created() {
    this.getDogBreed();
    DogService.getAllAgencies().then(response => {
        this.agencies = response.data;
        this.agencies.find(agency => {
          if (agency.agencyId === this.currentDog.agencyId) {
            this.currentAgency = agency
          }
        });
    });
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
    }
  }

}

</script>

<style scoped>
.polaroid-card {
  width: 525px;
  background-color: white;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  text-align: center;
  margin: 15px;
}

.polaroid-image-container {
  width: 100%;
  height: 400px;
  overflow: hidden;
  background-color: #f0f0f0;
  border-radius: 8px;
}

.polaroid-image-container img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.polaroid-caption {
  margin-top: 10px;
}

.polaroid-caption h2 {
  margin: 0 0 10px;
  font-size: 1.2em;
}

.polaroid-caption ul {
  list-style: none;
  padding: 0;
  margin: 0;
  font-size: 0.9em;
  color: #333;
}
</style>