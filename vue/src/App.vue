<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'adminpage' }" v-if="$store.state.token != ''">Admin</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'setcriteria'}"  v-if="$store.state.token != ''" >SetCriteria</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>

</style>
<script setup lang="ts">
</script>