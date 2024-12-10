<template>
  <div class="admin-box">
    <h2>Delete Dog</h2>
    <div>
      <label>Dog: </label>
      <select v-model="selectedDog" @click="getDogs">
        <option v-for="dog in dogs" :key="dog.dogId" :value="dog.dogName">
          {{ dog.dogName }}
        </option>
      </select>
    </div>
    <button @click.prevent="deleteDog" class="btn purple-btn">Delete Dog</button>
  </div>
</template>

<script>
import DogService from "../services/DogService";

export default {
  data() {
    return {
      selectedDog: "", // Changed to string for simpler dropdown binding
      dogs: [],
    };
  },
  created() {
    this.getDogs();
  },
  methods: {
    getDogs() {
      DogService.getAllDogs().then((response) => {
        this.dogs = response.data;
      });
    },
    deleteDog() {
      const dogToDelete = this.dogs.find(
        (dog) => dog.dogName === this.selectedDog
      );
      if (dogToDelete) {
        DogService.deleteDog(dogToDelete.dogId)
          .then(() => {
            this.selectedDog = ""; // Reset selection
            this.getDogs(); // Refresh dog list
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
  },
};
</script>

<style scoped>
/* Card Styling */
.admin-box {
  background-color: var(--purp4); /* Matches parent background */
  border: none; /* Removes any border */
  border-radius: 0; /* Removes rounded edges */
  box-shadow: none; /* Removes shadow effect */
  padding: 20px; /* Retain padding for proper spacing */
  text-align: center;
  color: white;
}

/* Title Styling */
.admin-box h2 {
  font-size: 1.5em;
  margin-bottom: 20px;
}

/* Label Styling */
.admin-box label {
  margin-right: 10px;
  font-weight: bold;
  color: white;
}

/* Dropdown Styling */
.admin-box select {
  width: 90%; /* Takes most of the card width */
  padding: 10px;
  margin-bottom: 15px;
  border: 1px solid var(--purp3);
  border-radius: 5px;
  font-size: 1em;
}

/* Button Styling */
.btn.purple-btn {
  padding: 8px 16px; /* Adjust padding for smaller size */
  font-size: 1em; /* Keep font size consistent */
  color: white;
  background-color: var(--purp1);
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  text-align: center; /* Centers the text inside the button */
  display: inline-block; /* Prevents it from stretching like a block element */
}

.btn.purple-btn:hover {
  background-color: var(--purp5); /* Lightens on hover */
}
</style>
