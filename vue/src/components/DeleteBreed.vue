<template>
    <div>
        <div>
            <label>Breed: </label>
            <select>
                <option v-for="breed in breeds" v-bind:key="breed.breedId" v-bind="this.selectedBreed">
                    {{ breed.breedName }}
                </option>
            </select>
        </div>
        <button v-on:click="this.deleteBreed()">Delete Breed</button>
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

        }
    }
}

</script>

<style scoped>
    label {
        margin: 10px;
    }
</style>