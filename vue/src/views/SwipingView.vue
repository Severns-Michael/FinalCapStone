<template>
    <dog-card v-bind:swipedBreed=breed></dog-card>
</template>

<script>
import DogCard from '../components/DogCard.vue';

export default {
    data() {
        return {
            breed: {userId: 1, breedId: 11, img: null, isYes: true}
        }
    },
    components: {
        DogCard
    }
}
</script>

<style scoped>

</style>