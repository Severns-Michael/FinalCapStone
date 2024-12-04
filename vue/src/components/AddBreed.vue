<template>
    <div class="form">
        <form v-on:submit.prevent="addBreed">
            <label for="breedName">Breed Name: </label>
            <input id="breedName" type="text" v-model="this.newBreed.officialName"/>
            <button type="submit">Submit</button>
        </form>
    </div>
</template>

<script>
import BreedService from '../services/BreedService';
export default {
    data() {
        return {
            newBreed: {
                breedName: '',
                subBreed: '',
                officialName: '',
                traits: []
            }
        }
        
    },
    methods: {
        addBreed() {
            BreedService.createBreed(this.newBreed).then(response => {
                if (response.status === 201) {
                    this.newBreed = {};
                }
            }).catch(error => {
                console.log(error);
            });
        }
    }
}

</script>

<style scoped>
    form {
        display: flex;
    }
    label {
        margin: 10px;
    }
</style>