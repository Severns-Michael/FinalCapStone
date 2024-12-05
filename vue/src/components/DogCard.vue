<template>
    <div class="container">
        <img v-bind:src="this.currentDog.img"/>
        <h2>{{ this.currentDog.dogName }}</h2>
        <ul>
            <li v-for="trait in this.currentDogBreed.traits" v-bind:key="trait.traitId"> {{ trait.traitName }} </li>
        </ul>
    </div>
</template>

<script>
import DogService from '../services/DogService';
import BreedService from '../services/BreedService';

export default {
    props: {
        swipedBreed: {}
    },
    data() {
        return {
            currentSwipedBreed: this.swipedBreed,
            currentDogBreed: {},
        }
    },
    beforeMount() {
        this.getDogBreed();
    },
    methods: {
        getDogBreed() {
            BreedService.getBreedById(this.currentSwipedBreed.breedId).then(response => {
                this.currentDogBreed = response.data;
                this.getDogPic();
            });
        },
        getDogPic() {
            if (this.currentSwipedBreed.img === null) {
                if (!this.currentDogBreed.subBreed === null) {
                    DogService.getSubBreedPic(this.currentDogBreed.breedName, this.currentDogBreed.subBreed).then(response => {
                        this.currentSwipedBreed.img = response.data.message
                    });
                } else {
                    DogService.getBreedPic(this.currentDogBreed.breedName).then(response => {
                        this.currentSwipedBreed.img = response.data.message
                    });
                }
            }
        }
    }
}
</script>

<style scoped>
    .container {
        border: 1px solid black;
    }
</style>