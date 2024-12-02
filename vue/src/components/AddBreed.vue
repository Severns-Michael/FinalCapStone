<template>
    <div class="form">
        <form v-on:sumbit.prevent="addBreed()">
            <label for="breedName">Breed Name: </label>
            <input id="breedName" type="text" v-model="this.newBreed.name"/>
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
                // need to assign new id and subbreed
                id: 0,
                name: '',
                subBreed: ''
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