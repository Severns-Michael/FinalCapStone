<template>
    <div class="container">
        <swipe-card v-bind:breed=this.breed></swipe-card>
    </div>
</template>

<script>
import SwipeCard from '../components/SwipeCard.vue';
import BreedService from '../services/BreedService';

export default {
    data() {
        return {
            randomBreeds: [],
            breed: {}
        }
    },
    components: {
        SwipeCard
    },
    created() {
        BreedService.getRandomBreeds().then(response => {
            this.randomBreeds = response.data
            this.getNextBreed();
        });
    },
    methods: {
        getNextBreed() {
            this.breed = this.randomBreeds.pop()
        }
    }
}
</script>

<style scoped>
.container {
    display: flex;
    justify-content: center;
    align-items: center;
}

</style>