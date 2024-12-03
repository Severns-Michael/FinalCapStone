<template>
    <form> 
        <div class="breed">
            <label>Breed: </label>
            <select v-model="this.selectedBreed">
                <option v-for="breed in this.breeds" v-bind:key="breed.breedName"></option>
            </select>
        </div>
        <div class="traits">
          <p class = "title">current traits</p>
          <p class = "title">all traits</p>
            <div class="listbox">
                <ul>
                    <li v-for="trait in currentTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"> <a href="#">{{ trait.traitName }}</a> </li>
                    <li>trait 1</li>
                    <li>trait 1</li>
                    <li>trait 1</li>
                </ul>
                <button class="switch" v-on:click="removeSelectedTraits"> Remove </button>
            </div>
            <div class="listbox">
                <button class="switch" v-on:click="addSelectedTraits"> Add </button>
                <ul>
                    <li v-for="trait in traits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"> <a href="#">{{ trait.traitName }}</a> </li> 
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
            breeds: this.$store.state.breeds,
            traits: [],
            currentTraits: [],
            selectedTraits: [],
            selectedBreed: {}
        }
    },
    created() {
        BreedService.getBreeds().then(response => {
            this.$store.state.breeds = response.data;
        }),
        TraitService.getTraits().then(response => {
            this.traits = response.data;
        })

    },
    methods: {
        removeSelectedTraits() {
            this.currentTraits.filter(trait => {
                if (!this.selectedTraits.includes(trait)) {
                    return trait;
                } else {
                    this.traits.push(trait);
                }
            });
        },
        addSelectedTraits() {

        },
        addToSelected(trait) {
            if (!this.selectedTraits.includes(trait)) {
                this.selectedTraits.push(trait);
            }
        }
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
      justify-content: space-evenly;
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
      height: 100px;
      list-style: none;
      overflow: scroll;
    }
    li{
        flex-basis: 80%;
        padding-right: 20px;
    }
    .switch {
        display: flex;
        height: 20%;
        width: 20%;
        align-items: center;
        flex-wrap: nowrap;
        margin: 25px;
    }

</style>