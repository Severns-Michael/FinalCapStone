
<template>
  <div class="form">
    <form v-on:submit.prevent="addDog()">
      <label for="breedName">Dog Name: </label>
      <input id="breedName" type="text" v-model="this.newDog.dogName"/>
      <button type="submit" class="btn purple-btn">Submit</button>
    </form>
  </div>
</template>


<script>
import dogService from "../services/DogService";
export default {
  data() {
    return {
      newDog: {
        dogName: '',
        breedId: '',
        age: '',
        gender: '',
        agencyId: '',
        img: ''
      }
    }
  },
  methods: {
    addDog() {
      dogService.createDog(this.newDog).then(response => {
        if (response.status === 201) {
          this.newDog = {
            dogName: '',
            breedId: '',
            age: '',
            gender: '',
            agencyId: '',
            img: ''
          };
        }
      })
          .catch(error => {
            console.log(error);
          });
    }
  }
}
</script>



<style scoped>
/* Form Container Styling */
form {
  display: flex;
  flex-direction: column; /* Stack label, input, and button vertically */
  align-items: center; /* Center the form elements */
  gap: 15px; /* Add space between each element */
  width: 100%; /* Ensure the form takes the full width of the box */
}

/* Label Styling */
label {
  font-size: 1em; /* Consistent font size with Delete Dog */
  font-weight: bold; /* Match bold styling */
  color: white; /* Matches the purple theme */
  text-align: left;
  width: 100%; /* Align label to the full width of the container */
}

/* Input Field Styling */
input {
  width: 90%; /* Keep it compact but responsive */
  padding: 10px; /* Add padding for a clean look */
  border: 1px solid var(--purp3); /* Matches your purple border styling */
  border-radius: 5px; /* Rounded corners for consistency */
  font-size: 1em; /* Matches dropdown size */
  color: var(--purp1); /* Text color aligns with the theme */
  background-color: white; /* Ensure readability */
  box-sizing: border-box; /* Ensures padding doesn’t overflow */
}

/* Button Styling */
.btn.purple-btn {
  width: auto; /* Adjust button width to fit content */
  padding: 10px 20px; /* Consistent padding */
  font-size: 1em; /* Same font size as Delete Dog */
  color: white; /* White text for contrast */
  background-color: var(--purp1); /* Matches the purple theme */
  border: none; /* Clean button look */
  border-radius: 5px; /* Rounded corners */
  cursor: pointer;
  transition: background-color 0.3s ease; /* Smooth hover transition */
}

.btn.purple-btn:hover {
  background-color: var(--purp5); /* Lightens on hover */
}


</style>