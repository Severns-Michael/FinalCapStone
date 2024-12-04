<template>
    <form> 
        <div class="breed">
            <label>Breed: </label>
            <select v-model="this.selectedBreed.officialName" @change="getSelectedBreed">
                <option v-for="breed in this.$store.state.breeds" v-bind:key="breed.breedName"> {{ breed.officialName }} </option>
            </select>
        </div>
        <div class="traits">
          <p class = "title">current traits</p>
          <p class = "title">all traits</p>
            <div class="listbox">
                <ul>
                    <li v-for="trait in currentTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"> <a href="#">{{ trait.traitName }}</a> </li>
                </ul>
                <button class="switchBtn" v-on:click.prevent="removeSelectedTraits"> Remove </button>
            </div>
            <div class="listbox">
                <button class="switchBtn" v-on:click.prevent="addSelectedTraits"> Add </button>
                <ul>
                    <li v-for="trait in traits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"> <a href="#">{{ trait.traitName }}</a> </li> 
                </ul>
            </div>
            <span class="saveBtn"><button class="save" v-on:click.prevent="updateBreed"> Save Changes </button></span>
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
          selectedBreedName: '',
          selectedBreed: {},
        }
    },
  // computed: {
  //     selectedBreed(){
  //     return this.breeds.find(breed=>{
  //       if(this.selectedBreedName === breed.name){
  //         this.currentTraits = breed.traits;
  //         return breed;
  //       }
  //     })
  //     }
  // },
    created(){
        BreedService.getBreeds().then(response => {
            this.$store.commit('SET_BREEDS', response.data);
        }),
        TraitService.getTraits().then(response => {
            this.traits = response.data;
        })
    },
    methods: {
      getSelectedBreed(){
         this.$store.state.breeds.find(breed => {
           if(breed.officialName === this.selectedBreed.officialName){
             this.selectedBreed = breed; // this gets the current breed's breedId, breedName, subBreed, and officialName
           }
         });
         BreedService.getBreedById(this.selectedBreed.breedId).then(response => {
            this.selectedBreed = response.data; // this gets the current breed's traits[] but still coming back empty bc its a list on server side (I think)
            this.currentTraits = this.selectedBreed.traits;
         });
      },
        removeSelectedTraits() {
            this.currentTraits = this.currentTraits.filter(trait => {
                if (!this.selectedTraits.includes(trait)) {
                    return trait;
                } else {
                    this.traits.push(trait);
                }
            });
            this.selectedTraits = [];
        },
        addSelectedTraits() {
            this.traits = this.traits.filter(trait => {
                if (!this.selectedTraits.includes(trait)) {
                    return trait;
                } else {
                    this.currentTraits.push(trait);
                }
            });
            this.selectedTraits = [];
        },
        addToSelected(trait) {
            if (!this.selectedTraits.includes(trait)) {
                this.selectedTraits.push(trait);
            }
        },
        updateBreed() {
          this.selectedBreed.traits =this.currentTraits;
            BreedService.updateBreed(this.selectedBreed).then(response => {
                if (response.status === 200) {
                    this.selectedBreed = {};
                    this.currentTraits = [];
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
        align-items: center;
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
      height: 30vh;
      width: 20vw;
      list-style: none;
      overflow: scroll;
    }
    li{
        flex-basis: 80%;
        padding-right: 20px;
    }
    .switchBtn {
        height: 20%;
        width: 40%;
        align-items: center;
        margin: 25px;
        text-align: center;
    }
    .saveBtn {
        flex-basis: 100%;
        flex-grow: 1;
        text-align: center; 
    }

</style>