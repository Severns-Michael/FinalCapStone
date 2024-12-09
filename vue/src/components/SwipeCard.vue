<template>
  <div class="container">

    <div class="card">

      <img draggable="false" v-bind:src="this.swipedBreed.img" class="card-img-top"/>

      <div class="card-body">
        <h2 class="card-title">{{ this.breed.officialName }}</h2>
      </div>

      <ul class="card-text">
        <li v-for="trait in this.breed.traits" v-bind:key="trait.traitId"> {{ trait.traitName }}</li>
      </ul>

      <div class="swipe-btns">
        <a draggable="false" href="" v-text="`I love ${this.breed.officialName}s!`" class="btn btn-primary swipe-btn" v-on:click="this.addToSwipedBreeds(true)"></a>
        <a draggable="false" v-text="'Not for me!'" class="btn btn-danger swipe-btn" v-on:click="this.addToSwipedBreeds(false)"></a>
      </div>

    </div>
  </div>
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
            swipedBreed: {
                userId: this.$store.state.user.id
            }

        }
    },
    beforeMount() {
        this.initializeSwipedBreed();
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
      addToSwipedBreeds(value) {
        this.swipedBreed.yes = value;
        DogService.addToSwipedBreeds(this.swipedBreed).then(response => {
          if (response.status === 201) {
            this.swipedBreed = {
                userId: this.$store.state.user.id
            };
          }
        });
        SwipingView.methods.getNextBreed();
      },
        initializeSwipedBreed() {
            if (this.breed && this.breed.breedId) {
                this.swipedBreed.breedId = this.breed.breedId;
                this.getDogPic();
            } else {
                console.warn('Breed data is not ready yet');
            }
        }

    },
    watch: {
        breed: {
            handler(newVal) {
                if (newVal && newVal.breedId) {
                    setTimeout(() => {this.initializeSwipedBreed()}, 100);
                }
            },
            immediate: true
        }
    }
}
</script>

<style scoped>

</style>