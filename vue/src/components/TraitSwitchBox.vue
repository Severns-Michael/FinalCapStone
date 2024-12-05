<template>
  <div class="traits">
    <p class = "title">My Dream Dog</p>
    <p class = "title">Puppy Possibilities</p>
    <p class = "title">Things to Avoid</p>
    <div class="listbox">
      <ul>
        <li v-for="trait in wantedTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)" v-bind:class="{selected: this.selectedTraits.includes(trait)}"> 
          <a href="#">{{ trait.traitName }}</a> 
        </li>
      </ul>
      <button class="switchBtn" v-on:click.prevent="removeSelectedWantedTraits"> Remove </button>
    </div>
    <div class="listbox">
      <button class="switchBtn" v-on:click.prevent="addSelectedWantedTraits"> Add </button>
      <ul>
        <li v-for="trait in traits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)" v-bind:class="{selected: this.selectedTraits.includes(trait)}"> 
          <a href="#">{{ trait.traitName }}</a> 
        </li>
      </ul>
      <button class="switchBtn" v-on:click.prevent="addSelectedUnwantedTraits"> Add </button>
    </div>
    <div class="listbox">
      <button class="switchBtn" v-on:click.prevent="removeSelectedUnwantedTraits"> Remove </button>
      <ul>
        <li v-for="trait in unwantedTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)" v-bind:class="{selected: this.selectedTraits.includes(trait)}"> 
          <a href="#">{{ trait.traitName }}</a> 
        </li>
      </ul>
    </div>
  </div>
</template>


<script>
import TraitService from "../services/TraitService";
import UserPreferencesService from "../services/UserPreferencesService";

export default{
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
.listbox {
  justify-content: space-evenly;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  margin: 5px;
  align-items: center;
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
  flex-basis: 33%;
  text-align: center;
}
ul{
  border: black solid 1px;
  display: flex;
  flex-direction: column;
  list-style: none;
  overflow: scroll;
  height: 40vh;
}
li{
  flex-basis: 80%;
  padding-right: 20px;
}
.switchBtn {
  height: 20%;
  width: 40%;
  margin: 25px;
  text-align: center;
  text-wrap: wrap;
  padding: 1px;
}
.saveBtn {
  flex-basis: 100%;
  flex-grow: 1;
  text-align: center;
}
.save {
  margin-top: 20px;
  margin-bottom: 20px;
  width: 15%;
  height: 100%;
}
.selected {
  background-color: aqua;
  opacity: 10%;
}

</style>