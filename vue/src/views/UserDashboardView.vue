<template>
  <div class="main-container">
    <!-- The "doggies" section serving as a banner -->
    <div class="Adoptable-Dogs">
      <h2>Potential Matches!</h2>
      <ul>
        <li v-for="dogId in currentDogList" :key="dogId">
          <dog-card-v2 :current-dog-list="dogId"></dog-card-v2>
        </li>
      </ul>
    </div>

    <!-- The container for "Breeds I Love" and "Breeds That Aren't For Me" -->
    <div class="container">
      <div class="acceptedList">
        <h2>Breeds I Love</h2>
        <ul>
          <li v-for="breed in yesBreeds" :key="breed.breedId">
            <breed-card :swipedBreed="breed"></breed-card>
          </li>
        </ul>
      </div>

      <div class="rejectedList">
        <h2>Breeds That Aren't For Me</h2>
        <ul>
          <li v-for="breed in noBreeds" :key="breed.breedId">
            <breed-card :swipedBreed="breed"></breed-card>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import BreedCard from '../components/BreedCard.vue';
import DogService from '../services/DogService';
import DogCardV2 from "../components/DogCardV2.vue";


export default {
    components: {
      DogCardV2,
      BreedCard
    },
    data() {
        return {
            yesBreeds: [],
            noBreeds: [],
            currentDogList: []
        }
    },
    created() {
        DogService.getSwipedBreeds().then(response => {
            response.data.forEach(swiped => {
                if (swiped.swipeYes) {
                    this.yesBreeds.push(swiped)
                } else {
                    this.noBreeds.push(swiped)
                }
            })
        });

        DogService.getAllDogs().then(response => {
          this.currentDogList = response.data;
        })
            .catch(error => {
              console.log(error);
            });
    }

}
</script>

<style scoped>

:root {
    --purp1: #4B0365 !important;
    --purp2: #600581 !important;
    --purp3: #7c08a6 !important;
    --purp4: #8a2cac !important;
    --purp5: #a04ebd !important;
}
.main-container {
  display: flex;
  flex-direction: column; /* Stacks sections vertically */
  width: 100%;
  align-items: center;
}

.Adoptable-Dogs {
  display: flex;
  flex-direction: column; /* Ensures title and list are in a column */
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
  width: 90%;
  background-color: var(--purp4);
  border-radius: 20px;
}

.Adoptable-Dogs ul {
  display: flex;
  flex-direction: row; /* Ensures list items are horizontal */
  overflow-x: auto; /* Adds horizontal scrolling if needed */
  flex-basis: 95%;
  width: 100%;
  padding: 0;
  margin: 0;
  margin-bottom: 5vh;
}

.container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
  margin-top: 20px;
}

.acceptedList, .rejectedList {
  flex: 1;
  margin: 10px;
  background-color: var(--purp4);
  border-radius: 20px;
  width: 50%;
  
}

ul {
  list-style: none;
  padding: 0;
}

li {
  margin-right: 10px; /* Adjust space between dog cards */
}

h2 {
  text-align: center;
  text-decoration: underline;
  font-weight: bold;
  margin-bottom: 10px;
  margin-top: 20px;
  color: aliceblue;
}

.acceptedList > ul, .rejectedList > ul {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-evenly;
    align-items: center;
}

.acceptedList > li, .rejectedList > li {
    flex-basis: 45%;
}

</style>