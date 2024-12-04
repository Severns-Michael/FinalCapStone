<template>
    <div class="container">
        <div>
            <label>Breed: </label>
            <select v-model="this.selectedBreed.officialName">
                <option v-for="breed in breeds" v-bind:key="breed.breedId" v-bind="this.selectedBreed">
                    {{ breed.officialName }}
                </option>
            </select>
        </div>
        <button v-on:click.prevent="deleteBreed">Delete Breed</button>
    </div>
</template>

<script>
import BreedService from '../services/BreedService';

export default {
    data() {
        return {
            breeds: [],
            selectedBreed: {}
        }
    },
    created() {
        BreedService.getBreeds().then(response => {
            this.breeds = response.data;
        })
    },
    methods: {
        deleteBreed() {
            this.breeds.find(breed => {
                if (breed.officialName === this.selectedBreed.officialName) {
                    this.selectedBreed = breed;
                }
            });
            BreedService.deleteBreed(this.selectedBreed.breedId).then(response => {
                this.selectedBreed = {};
                BreedService.getBreeds().then(response => {
                    this.breeds = response.data;
                });
            }).catch(error => {
                console.log(error);
            });
        }
    }
}

</script>

<style scoped>
    label {
        margin: 10px;
    }
    .container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        flex-basis: 60%;
    }
</style>