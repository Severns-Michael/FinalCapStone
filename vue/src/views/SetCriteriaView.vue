<template>
  <div class="container">
    <div class="trait-picker">

      <div class="trait-list">
        <h3>My Dream Dog</h3>
        <ul>
          <li v-for="trait in alphabetizedWantedTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"
              v-bind:class="{selected: this.selectedTraits.includes(trait)}">
            {{ trait.traitName }}
          </li>
        </ul>
        <div class="spacer"></div>
      </div>
      <div class="spacer" id="buttonSpacer">
        <button class="btn purple-btn" v-on:click.prevent="addSelectedWantedTraits">« Add to Liked</button>
        <button class="btn purple-btn" v-on:click.prevent="removeSelectedWantedTraits">Remove »</button>
        <div class="slidersSize">
      <age-slider></age-slider>
    </div>
      </div>


      <div class="trait-list">
        <h3>Puppy Possibilities</h3>
        <ul style="width: 15vw">
          <li v-for="trait in alphabetizedTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"
              v-bind:class="{selected: this.selectedTraits.includes(trait)}">
            {{ trait.traitName }}
          </li>
        </ul>
      </div>

      <div class="spacer" id="buttonSpacer">
        <button class="btn purple-btn" v-on:click.prevent="addSelectedUnwantedTraits" style="font-size: 0.9em;">Add to Disliked»</button>        <button class="btn purple-btn" v-on:click.prevent="removeSelectedUnwantedTraits">« Remove</button>
        <div class="slidersLifespan">
      <slider sliderType="Dog Size"></slider>
    </div>
    
      </div>

      <div class="trait-list">
        <h3>Not for me</h3>
        <ul>
          <li v-for="trait in alphabetizedUnwantedTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"
              v-bind:class="{selected: this.selectedTraits.includes(trait)}">
            {{ trait.traitName }}
          </li>
        </ul>
          <div class="spacer"></div>
      </div>

    </div>
  
  </div>
</template>

<script>
import Slider from "../components/Slider.vue"
import AgeSlider from "../components/AgeSlider.vue";
import TraitService from "../services/TraitService";
import UserPreferencesService from "../services/UserPreferencesService";

export default {
  components: {
    Slider,
    AgeSlider
  },
  data(){
    return {
      traits: [],
      selectedTraits: [],
      wantedTraits: [],
      unwantedTraits: [],
      filteredTraits: []
    }
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
        alphabetizedWantedTraits() {
            const alphaTraits = this.wantedTraits;
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
        alphabetizedUnwantedTraits() {
            const alphaTraits = this.unwantedTraits;
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
  created() {
    TraitService.getTraits().then(response => {
      this.traits = response.data;
      this.getYesTraits();
    });
  },
  methods:{
    removeSelectedWantedTraits() {
      this.wantedTraits = this.wantedTraits.filter(trait => {
        if (!this.selectedTraits.includes(trait)) {
          return trait;
        } else {
          this.traits.push(trait);
        }
      });
      this.selectedTraits = [];
      this.updateUserPreferences();
    },
    addSelectedWantedTraits() {
      this.traits = this.traits.filter(trait => {
        if (!this.selectedTraits.includes(trait)) {
          return trait;
        } else {
          this.wantedTraits.push(trait);
        }
      });
      this.selectedTraits = [];
      this.updateUserPreferences();
    },
    addToSelected(trait) {
      if (!this.selectedTraits.includes(trait)) {
        this.selectedTraits.push(trait);
      } else {
        const index = this.selectedTraits.indexOf(trait);
        this.selectedTraits.splice(index, 1);
      }
    },
    removeSelectedUnwantedTraits() {
      this.unwantedTraits = this.unwantedTraits.filter(trait => {
        if (!this.selectedTraits.includes(trait)) {
          return trait;
        } else {
          this.traits.push(trait);
        }
      });
      this.selectedTraits = [];
      this.updateUserPreferences();
    },
    addSelectedUnwantedTraits() {
      this.traits = this.traits.filter(trait => {
        if (!this.selectedTraits.includes(trait)) {
          return trait;
        } else {
          this.unwantedTraits.push(trait);
        }
      });
      this.selectedTraits = [];
      this.updateUserPreferences();
    },
    updateUserPreferences() {
      UserPreferencesService.updateYesTraits(this.wantedTraits);
      UserPreferencesService.updateNoTraits(this.unwantedTraits);
    },
    getYesTraits() {
      UserPreferencesService.getYesTraits().then(response => {
        this.wantedTraits = response.data;
        this.getNoTraits();
      })
    },
    getNoTraits() {
      UserPreferencesService.getNoTraits().then(response => {
        this.unwantedTraits = response.data;
        this.traits.forEach(allTrait => {
          const isTraitPresent = this.unwantedTraits.some(unwantTrait => unwantTrait.traitId === allTrait.traitId) || this.wantedTraits.some(wantTrait => wantTrait.traitId === allTrait.traitId);
          if (!isTraitPresent) {
            this.filteredTraits.push(allTrait);
          }
        });
        this.traits = this.filteredTraits;
        this.filteredTraits = [];
      })
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
  .container {
    display: flex;
    flex-direction: column;
  }

  .traitSwitcher {
    margin: 70px;
  }

  .sliders {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
  }

  .purple-btn {
    padding: 10px 20px;
    font-size: 1em;
    color: white;
    background-color: var(--purp1);
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .purple-btn:hover {
    background-color: var(--purp5);
    color: white;
  }

  .purple-slider {
    padding: 10px;
    font-size: 1em;
    color: white;
    background-color: var(--purp1);
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-align: center;
  }

  .purple-slider:hover {
    background-color: var(--purp5);
  }
</style>