<template>
  <div class="container">
    <router-link v-bind:to="{name: 'setcriteria'}"> Set Preferences </router-link>

    <ul class="selectedDogList">
      <li v-for="dog in this.yesDogs" v-bind:key="dog.dogId">
        <dog-card v-bind:dog="dog"></dog-card>
      </li>
    </ul>
  </div>
</template>

<script>
import DogCard from '../components/DogCard.vue';
import DogService from '../services/DogService';

export default {
    components: {
        DogCard
    },
    data() {
        return {
            yesDogs: [{dogId: 1, dogName: 'Rufus', breedId: 11, age: 10, size: 2, img: null}]
        }
    },
    created() {
        DogService.getDogsYes().then(response => {
            this.yesDogs = response.data;
        });
    }
}
</script>

<style scoped>
ul {
    list-style: none;
}

</style>