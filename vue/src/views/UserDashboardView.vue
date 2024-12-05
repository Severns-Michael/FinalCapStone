<template>
    <router-link v-bind:to="{name: 'setcriteria'}"> Set Preferences </router-link>

    <ul class="selectedDogList">
        <li v-for="breed in this.yesBreeds" v-bind:key="breed.breedId">
            <dog-card v-bind:swipedBreed="breed"></dog-card>
        </li>
    </ul>
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
            yesBreeds: []
        }
    },
    created() {
        DogService.getSwipedBreeds().then(response => {
            response.data.forEach(swiped => {
                if (swiped.yes) {
                    this.yesBreeds.push(swiped)
                }
            })
        });
    }
}
</script>

<style scoped>
ul {
    list-style: none;
}

</style>