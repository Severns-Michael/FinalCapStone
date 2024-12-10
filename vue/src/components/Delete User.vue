<template>
  <div class="container">
    <div>
      <label>User: </label>
      <select v-model="this.selectedBreed.officialName" v-on:click="getBreeds()">
        <option v-for="user in users" v-bind:key="breed.breedId" v-bind="this.selectedBreed">
          {{ }}
        </option>
      </select>
    </div>
    <button v-on:click.prevent="deleteUser" class="btn btn-light">Delete User</button>
  </div>
</template>

<script >
export default {
methods:{
  deleteUser(){

  }
}

</script>

<style scoped>

</style>