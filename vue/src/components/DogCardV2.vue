<template>
  <div class="polaroid-card">
    
    <div class="polaroid-image-container">
      <img v-bind:src="this.currentDog.img" alt="Dog photo" />
    </div>


    <div class="polaroid-caption">
      <h2>{{ this.currentDog.dogName }}</h2>
      <ul>
        <li>Breed: {{ this.currentDogBreed.officialName }}</li>
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
    currentDogProp: {}
  },
  data() {
    return {
      currentDog: this.currentDogProp,
      agencies: [],
      currentAgency: {},
      currentDogBreed: {}
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
      BreedService.getBreedById(this.currentDogProp.breedId).then(response => {
        this.currentDogBreed = response.data;
        if (this.currentDogProp.img === null) {
          if (!this.currentDogBreed.subBreed === null) {
            DogService.getSubBreedPic(this.currentDogBreed.breedName, this.currentDogBreed.subBreed).then(response => {
              this.currentDog.img = response.data.message
            });
          } else {
            DogService.getBreedPic(this.currentDogBreed.breedName).then(response => {
              this.currentDog.img = response.data.message
            });
          }
        }
      })
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
  width: 535px;
  background-color: white;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  text-align: center;
  margin: 15px;
}

.polaroid-image-container {
  width: 100%;
  height: 325px;
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