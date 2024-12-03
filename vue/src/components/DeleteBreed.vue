<template>
    <div class="container">
        <div>
            <label>Breed: </label>
            <select>
                <option v-for="breed in breeds" v-bind:key="breed.breedId" v-bind="this.selectedBreed">
                    {{ breed.officialName }}
                </option>
            </select>
        </div>
        <button v-on:click="deleteBreed">Delete Breed</button>
    </div>
</template>

<script>
import BreedService from '../services/BreedService';

export default {
    data() {
        return {
            breeds: this.$store.state.breeds,
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
            BreedService.deleteBreed(this.selectedBreed.breedId).then(response => {
                if (response.status === 200) {
                    this.selectedBreed = {};
                }
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
    }
</style>