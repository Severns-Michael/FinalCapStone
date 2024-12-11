<template>


  <div class="card drag-card" draggable="true" id="drag-card" ondragstart="pickupHandler(event)"
       ondragover="hoverHandler(event)" ondrop="dropHandler(event)">
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
  }
}
</script>

<style scoped>

</style>