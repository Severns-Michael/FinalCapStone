<template>
    <div class="container">

    <div class="card">
    <img v-bind:src="this.swipedBreed.img" class="card-img-top"/>
    <div class="card-body">
        <h2 class="card-title">{{ this.breed.officialName }}</h2>
    </div>
    <ul class="card-text">
        <li v-for="trait in this.breed.traits" v-bind:key="trait.traitId"> {{ trait.traitName }} </li>
    </ul>
    <div class="card-swipe">
    <a href="" v-text="`I love ${this.breed.officialName}!`" class="btn btn-primary" v-on:click="this.addToSwipedBreeds(true)"></a>
    <a href="" v-text="'Not for me!'" class="btn btn-danger" v-on:click="this.addToSwipedBreeds(false)"></a>
  </div>
</div>
</div>
</template>

<script>
import DogService from '../services/DogService';

export default {
    props: {
        breed: {}
    },
    data() {
        return {
            swipedBreed: {}

        }
    },
    mounted() {
        this.initializeSwipedBreed();
    },
    methods: {
        getDogPic() {
                if (!this.breed.subBreed === null) {
                    DogService.getSubBreedPic(this.breed.breedName, this.breed.subBreed).then(response => {
                        this.swipedBreed.img = response.data.message
                    });
                } else {
                    DogService.getBreedPic(this.breed.breedName).then(response => {
                        this.swipedBreed.img = response.data.message
                    });
                }
        },
        addToSwipedBreeds(value) {
            this.swipedBreed.yes = value;
            DogService.addToSwipedBreeds(this.swipedBreed).then(response => {
                if (response.status === 201) {
                    this.swipedBreed = {};
                }
            });
        },
        initializeSwipedBreed() {
            this.swipedBreed.breedId = this.breed.breedId;
            this.getDogPic();
        }

    }
}
</script>

<style scoped>

</style>