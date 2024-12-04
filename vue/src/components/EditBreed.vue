<template>
    <form> 
        <div class="breed">
            <label>Breed: </label>
            <select v-model="this.selectedBreed.officialName" @change="getSelectedBreed" v-on:click="getBreeds">
                <option v-for="breed in this.breeds" v-bind:key="breed.breedName"> {{ breed.officialName }} </option>
            </select>
        </div>
            <div class="traits">
  <div class="list-group">
    <p class="title">Current Traits</p>
    <div class="listbox">
      <ul>
        <li v-for="trait in currentTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)" v-bind:class="{selected: this.selectedTraits.includes(trait)}">
          <a href="#">{{ trait.traitName }}</a>
        </li>
      </ul>
      <button class="switchBtn" v-on:click.prevent="removeSelectedTraits">Remove</button>
    </div>
  </div>

  <div class="list-group">
    <p class="title1">All Traits</p>
    <div class="listbox">
      <button class="switchBtn" v-on:click.prevent="addSelectedTraits">Add</button>
      <ul>
        <li v-for="trait in traits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)" v-bind:class="{selected: this.selectedTraits.includes(trait)}">
          <a href="#">{{ trait.traitName }}</a>
        </li>
      </ul>
    </div>
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
            filteredTraits: [],
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
            this.breeds = response.data;
        }),
        TraitService.getTraits().then(response => {
            this.traits = response.data;
        })
    },
    methods: {
        getBreeds() {
            BreedService.getBreeds().then(response => {
                this.breeds = response.data;
            });
        },
      getSelectedBreed(){
         this.breeds.find(breed => {
           if(breed.officialName === this.selectedBreed.officialName){
             this.selectedBreed = breed; 
           }
         });
         BreedService.getBreedById(this.selectedBreed.breedId).then(response => {
            this.selectedBreed = response.data; 
            console.log(this.selectedBreed.traits);
            this.currentTraits = this.selectedBreed.traits;
                this.traits.forEach(allTrait => {
                    this.currentTraits.forEach(currTrait => {
                        if (!currTrait.traitId === allTrait.traitId) {
                            this.filteredTraits.push(allTrait);
                            
                        }
                    });
                });
            
            this.traits = this.filteredTraits;
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
        },
        isSelected() {
            if (this.selectedTraits.includes(this.trait)) {
                return true;
            } else {
                return false;
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
      margin-right: 45%;
    }
    .title1{
      text-decoration: underline;
      flex-basis: 50%;
      text-align: center;
      margin-left: 40%;

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
    .selected {
        background-color: aqua;
        opacity: 10%;
    }
</style>