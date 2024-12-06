<template>
  <div class="container">
    <div class="trait-picker">

      <div class="trait-list">
        <h3>My Dream Dog</h3>
        <ul>
          <li v-for="trait in wantedTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"
              v-bind:class="{selected: this.selectedTraits.includes(trait)}">
            {{ trait.traitName }}
          </li>
        </ul>
        <div class="spacer"></div>
        <button class="btn btn-light" v-on:click.prevent="removeSelectedWantedTraits">Remove »</button>
      </div>
      <div class="spacer"></div>


      <div class="trait-list">
        <h3>Puppy Possibilities</h3>
        <ul>
          <li v-for="trait in traits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"
              v-bind:class="{selected: this.selectedTraits.includes(trait)}">
            {{ trait.traitName }}
          </li>
        </ul>
        <span style="display: flex;justify-content: space-between;">
        <button class="btn btn-light" v-on:click.prevent="addSelectedWantedTraits" style="width: 40%">« Add to Liked</button>
        <button class="btn btn-light" v-on:click.prevent="addSelectedUnwantedTraits" style="width: 40%">Add to Disliked»</button>

        </span>

      </div>

      <div class="spacer"></div>

      <div class="trait-list">
        <h3>Not for me</h3>
        <ul>
          <li v-for="trait in unwantedTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"
              v-bind:class="{selected: this.selectedTraits.includes(trait)}">
            {{ trait.traitName }}
          </li>
        </ul>
        <button class="btn btn-light" v-on:click.prevent="removeSelectedUnwantedTraits">« Remove</button>
      </div>

    </div>
    <div class="sliders">
      <slider sliderType="Dog Size"></slider>
      <age-slider></age-slider>
    </div>
  </div>
</template>

<script>
import Slider from "../components/Slider.vue"
import AgeSlider from "../components/AgeSlider.vue";
import TraitService from "@/services/TraitService";
import UserPreferencesService from "@/services/UserPreferencesService";

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
    }
  },
  computed: {

  },
  created() {
    TraitService.getTraits().then(response => {
      this.traits = response.data;
    })
    UserPreferencesService.getYesTraits().then(response => {
      this.wantedTraits = response.data;
    }),
        UserPreferencesService.getNoTraits().then(response => {
          this.unwantedTraits = response.data;
        })
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
      this.updateUserPreferences;
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
      this.updateUserPreferences;
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
      this.updateUserPreferences;
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
      this.updateUserPreferences;
    },
    updateUserPreferences() {
      UserPreferencesService.updateYesTraits(this.wantedTraits);
      UserPreferencesService.updateNoTraits(this.unwantedTraits);
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

</style>