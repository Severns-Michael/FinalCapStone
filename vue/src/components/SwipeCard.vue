<template>



  <div class="card drag-card" draggable="false" id="drag-card" >
    <img draggable="false" class="card-img-top" :src="this.swipedBreed.img"/>
    <div class="card-body">
      <h2 v-text="this.currentBreed.officialName"></h2>
      <ul>
        <li v-for="trait in this.currentBreed.traits" v-bind:key="trait.traitId" v-text="trait.traitName"></li>
      </ul>
    </div>
  </div>
  <!---------------------------------------------------------------------------------------->


</template>

<script>

export default {
  props: {
    swipedBreed: {
      type: Object,
      required: true
    },
    currentBreed: {
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