<template>
  <form class="breed">


    <div class="breed-selector">
      <label>Breed: </label>
      <select v-model="this.selectedBreed.officialName" @change="getSelectedBreed" v-on:click="getBreeds">
        <option v-for="breed in this.breeds" v-bind:key="breed.breedName"> {{ breed.officialName }}</option>
      </select>
    </div>


    <div class="trait-switcher">
      <div class="trait-list">
        <h3>Current Traits</h3>
        <ul>
          <li v-for="trait in alphabetizedCurrentTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"
              v-bind:class="{selected: this.selectedTraits.includes(trait)}">
            {{ trait.traitName }}
          </li>
        </ul>

        <div class="spacer"></div>
        <button class="btn purple-btn" v-on:click.prevent="removeSelectedTraits">Remove &rarr;</button>
      </div>


      <div class="trait-list">
        <h3>All Traits</h3>
        <ul>
          <li v-for="trait in alphabetizedTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"
              v-bind:class="{selected: this.selectedTraits.includes(trait)}">
            {{ trait.traitName }}
          </li>
        </ul>
        <div class="spacer"></div>
        <button class="btn purple-btn" v-on:click.prevent="addSelectedTraits">&larr; Add</button>

      </div>

      <!--trait switcher div-->
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
          selectedBreed: this.$store.state.selectedBreed
        }
    },
    created(){
        BreedService.getBreeds().then(response => {
            this.breeds = response.data;
        }),
        TraitService.getTraits().then(response => {
            this.traits = response.data;
        })
    },
    computed: {
        alphabetizedTraits() {
            const alphaTraits = this.traits;
            alphaTraits.sort((a, b) => {
              const nameA = a.traitName.toUpperCase(); 
              const nameB = b.traitName.toUpperCase(); 
              if (nameA < nameB) {
                return -1;
              }
              if (nameA > nameB) {
                return 1;
              }
              return 0;
            });
            return alphaTraits;
        },
        alphabetizedCurrentTraits() {
            const alphaTraits = this.currentTraits;
            alphaTraits.sort((a, b) => {
              const nameA = a.traitName.toUpperCase(); 
              const nameB = b.traitName.toUpperCase(); 
              if (nameA < nameB) {
                return -1;
              }
              if (nameA > nameB) {
                return 1;
              }
              return 0;
            });
            return alphaTraits;
        }
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
         TraitService.getTraits().then(response => {
            this.traits = response.data;
         });
         BreedService.getBreedById(this.selectedBreed.breedId).then(response => {
            this.selectedBreed = response.data; 
            this.currentTraits = this.selectedBreed.traits;
            this.traits.forEach(allTrait => {
                const isTraitPresent = this.currentTraits.some(currTrait => currTrait.traitId === allTrait.traitId);
                if (!isTraitPresent) {
                    this.filteredTraits.push(allTrait);
                }
            });
            this.traits = this.filteredTraits;
            this.filteredTraits = [];
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
            this.updateBreed();
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
            this.updateBreed();
            this.selectedTraits = [];
        },
        addToSelected(trait) {
            if (!this.selectedTraits.includes(trait)) {
                this.selectedTraits.push(trait);
            } else {
                const index = this.selectedTraits.indexOf(trait);
                this.selectedTraits.splice(index, 1);
            }
        },
        updateBreed() {
          this.selectedBreed.traits =this.currentTraits;
            BreedService.updateBreed(this.selectedBreed).then(response => {
                if (response.status === 200) {
                    // this.selectedBreed = {};
                    // this.currentTraits = [];
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
    .saveBtn {
        flex-basis: 100%;
        flex-grow: 1;
        text-align: center; 
    }
    .purple-btn {
    padding: 8px 16px; 
    font-size: 1em; 
    color: white;
    background-color: var(--purp1);
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    width: auto;  
    text-align: center;
    box-sizing: border-box; 
}
.purple-btn:hover {
    background-color: var(--purp4);
    color: white;
}
    

</style>