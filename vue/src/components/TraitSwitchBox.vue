<template>
  <div class="traits">
    <p class = "title">wanted traits</p>
    <p class = "title">all traits</p>
    <p class = "title">unwanted traits</p>
    <div class="listbox">
      <ul>
        <li v-for="trait in wantedTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"> <a href="#">{{ trait.traitName }}</a> </li>
        <li>trait 1</li>
        <li>trait 2</li>
        <li>trait 3</li>
      </ul>
      <button class="switchBtn" v-on:click.prevent="removeSelectedWantedTraits"> Remove </button>
    </div>
    <div class="listbox">
      <button class="switchBtn" v-on:click.prevent="addSelectedWantedTraits"> Add </button>
      <ul>
        <li v-for="trait in traits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"> <a href="#">{{ trait.traitName }}</a> </li>
      </ul>
      <button class="switchBtn" v-on:click.prevent="addSelectedUnwantedTraits"> Add </button>
    </div>
    <div class="listbox">
      <button class="switchBtn" v-on:click.prevent="removeSelectedUnwantedTraits"> Remove </button>
      <ul>
        <li v-for="trait in unwantedTraits" v-bind:key="trait.traitId" v-on:click="addToSelected(trait)"> <a href="#">{{ trait.traitName }}</a> </li>
        <li>trait 1</li>
        <li>trait 2</li>
        <li>trait 3</li>
      </ul>
    </div>
    <span class="saveBtn"><button class="save" v-on:click.prevent="updateBreed"> Save Changes </button></span>
  </div>
</template>


<script>
import TraitService from "../services/TraitService";

export default{
  data(){
    return {
      traits: [],
      selectedTraits: [],
      wantedTraits: [],
      unwantedTraits: [],
    }
  },
  created() {
    TraitService.getTraits().then(response => {
      this.traits = response.data;
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
    },
    addToSelected(trait) {
      if (!this.selectedTraits.includes(trait)) {
        this.selectedTraits.push(trait);
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
    },
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
  align-items: center;
  margin: 25px;
  text-align: center;
}
.saveBtn {
  flex-basis: 100%;
  flex-grow: 1;
  text-align: center;
}
.save {
  margin-top: 20px;
  margin-bottom: 20px;
  width: 25%;
  height: 80%;
}

</style>