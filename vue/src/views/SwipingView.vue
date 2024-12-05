<template>
    <dog-card v-bind:swipedBreed=this.breed></dog-card>
</template>

<script>
import DogCard from '../components/DogCard.vue';
import BreedService from '../services/BreedService';

export default {
    data() {
        return {
            randomBreeds: [],
            breed: {}
        }
    },
    components: {
        DogCard
    },
    created() {
        BreedService.getRandomBreeds().then(response => {
            this.randomBreeds = response.data
        })
    },
    methods: {
        getNextBreed() {
            this.breed = this.randomBreeds.pop()
        }
    }
}
</script>

<style scoped>

</style>