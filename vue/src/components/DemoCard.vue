<template>


  <div class="card drag-card" draggable="false" id="drag-card">
    <img draggable="false" class="card-img-top" src="https://pet-health-content-media.chewy.com/wp-content/uploads/2024/09/11181054/202104doberman-pinscher-dog-breed.jpg"/>
    <div class="card-body">
      <h2>Doberman</h2>
      <ul>
        <li>funny</li>
        <li>chill</li>
        <li>loves cats</li>
      </ul>
    </div>
  </div>
  <!---------------------------------------------------------------------------------------->


</template>

<script>
// import DogService from '../services/DogService';
// import SwipingView from '../views/SwipingView.vue';
//
// export default {
//   props: {
//     breed: {
//       type: Object,
//       required: true
//     }
//   },
//   data() {
//     return {
//
//     }
//   },
//   beforeMount() {
//   },
//   methods: {
//     getDogPic() {
//       if (!this.breed.subBreed === null) {
//         DogService.getSubBreedPic(this.breed.breedName, this.breed.subBreed).then(response => {
//           this.swipedBreed.img = response.data.message
//         });
//       } else {
//         DogService.getBreedPic(this.breed.breedName).then(response => {
//           this.swipedBreed.img = response.data.message
//         });
//       }
//     },
//
//
//   }
//
// }
</script>

<style scoped>

</style>