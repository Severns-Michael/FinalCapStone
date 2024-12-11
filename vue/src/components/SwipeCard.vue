<template>


  <div class="card drag-card" draggable="true" id="drag-card" ondragstart="pickupHandler(event)"
       ondragover="hoverHandler(event)" ondrop="dropHandler(event)">
    <img draggable="false" class="card-img-top" :src="this.breed.img"/>
    <div class="card-body">
      <h2 v-text="this.breed.officialName"></h2>
      <ul>
        <li v-for="trait in this.breed.traits" v-text="trait.traitName"></li>
      </ul>
    </div>
  </div>
  <!---------------------------------------------------------------------------------------->


</template>

<script>
import DogService from '../services/DogService';
import SwipingView from '../views/SwipingView.vue';

export default {
  props: {
    breed: {
      type: Object,
      required: true
    }
  },
  data() {
    return {

    }
  },
  beforeMount() {
  },
  methods: {
    getDogPic() {
      if (!this.breed.subBreed === null) {
        DogService.getSubBreedPic(this.breed.breedName, this.breed.subBreed).then(response => {
          this.swipedBreed.img = response.data.message
        });
      } else {
        DogService.getBreedPic(this.breed.breedName).then(response => {
          this.swipedBreed.img = response.data.message
        });
      }
    },


  }

}
</script>

<style scoped>

</style>