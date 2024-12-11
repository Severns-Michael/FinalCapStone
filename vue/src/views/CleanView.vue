<template>
  <div class="fh-box fh-row">

    <div class="swipe" id="no-div">
      no div
    </div>

    <div class="swipe swipe-dog" id="dog-div">

      <!--    <div class="card drag-card" draggable="true" id="drag-card" ondragstart="pickupHandler(event)"-->
      <!--         ondragover="hoverHandler(event)" ondrop="dropHandler(event)">-->
      <!--      <img draggable="false" class="card-img-top" :src="this.currentBreed.imgpath"/>-->
      <!--      <div class="card-body">-->
      <!--        <h2 v-text="this.currentBreed.officialName"></h2>-->
      <!--        <ul>-->
      <!--          <li v-for="trait in this.currentBreed.traits" v-text="trait.traitName"></li>-->
      <!--        </ul>-->
      <!--      </div>-->
      <!--    </div>-->
      <demo-card/>
    </div>
    <div class="swipe" ondragover="hoverHandler(event)" @drop="swipeNo($event,this.currentBreed)" id="yes-div">
      yes div
    </div>
  </div>
</template>
<script>
import DemoCard from '@/components/DemoCard.vue'

export default {
  components: {
    DemoCard,
  }
}
</script>