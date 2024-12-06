<template>
    <div class="container">
        <div class="acceptedList">
            <h2> Breeds I Love </h2>
            <ul>
                <li v-for="breed in this.yesBreeds" v-bind:key="breed.breedId">
                    <dog-card v-bind:swipedBreed="breed"></dog-card>
                </li>
            </ul>
        </div>
        
        <div class="rejectedList">
            <h2> Breeds That Aren't For Me </h2>
            <ul>
                <li v-for="breed in this.noBreeds" v-bind:key="breed.breedId">
                    <dog-card v-bind:swipedBreed="breed"></dog-card>
                </li>
            </ul>
        </div>
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
            yesBreeds: [],
            noBreeds: []
        }
    },
    created() {
        DogService.getSwipedBreeds().then(response => {
            response.data.forEach(swiped => {
                if (swiped.yes) {
                    this.yesBreeds.push(swiped)
                } else {
                    this.noBreeds.push(swiped)
                }
            })
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

ul {
    list-style: none;
    overflow-y: scroll;
    height: 80vh;
}

.container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    margin-top: 50px;
    margin-bottom: 50px;
}

.rejectedList {
    flex-basis: 50%;
    margin-left: 50px;
}

.acceptedList {
    flex-basis: 50%;
    margin-right: 50px;

}

h2 {
    text-decoration: underline;
    font-weight: bold;
    margin-bottom: 30px;
    padding: 10px;
}

</style>