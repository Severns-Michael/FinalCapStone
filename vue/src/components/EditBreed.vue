<template>
    <form> 
        <div class="breed">
            <label>Breed: </label>
            <select v-model="this.selectedBreed">
                <option v-for="breed in this.breeds" v-bind:key="breed.id">
                    {{ breed.breed_name }}
                </option>
            </select>
        </div>
        <div class="traits">
          <p class = "title">current traits</p>
          <p class = "title">all traits</p>
            <div class="listbox">
                <ul>
                    <li>trait 1</li>
                    <li>trait 2</li>
                    <li>trait 3</li>
                </ul>
                
            </div>
        
            <div class="listbox">
                <ul>
                    <li v-for="trait in traits" v-bind:key="trait.id" ></li> 
                </ul>
            </div>
        </div>
    </form>
</template>

<script>
import BreedService from '../services/BreedService';
import TraitService from '../services/TraitService';

export default {
    data() {
        return {
            breeds: [],
            traits: [],
            selectedBreed: ''
        }
    },
    created() {
        BreedService.getBreeds().then(response => {
            this.breeds = response.data;
        }),
        TraitService.getTraits().then(response => {
            this.traits = response.data;
        })

    }
}

</script>

<style scoped>
    form {
        display: flex;
        flex-direction: column;
        flex-wrap: wrap;
      width: 70%;

        
    }
    .listbox {
      justify-content: space-between;
      display: flex;
      flex-direction: row;
      flex-wrap: nowrap;
      margin: 5px;

    }
    label {
        margin: 10px;
    }
    .breed {
       flex-basis: 100%;
      text-align: center;
    }
    .traits {
      flex-basis: 50%;
      justify-content: space-evenly;
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
    }
    .title{
      text-decoration: underline;
      flex-basis: 50%;
      text-align: center;
    }
    ul{
      border: black solid 1px;
      display: flex;
      flex-direction: column;
      order: 6;
      height: 100px;
      list-style: none;
    }
    li{
        flex-basis: 80%;
        padding-right: 20px;
    }

</style>