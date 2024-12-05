<template>
  <div id="capstone-app">
    <div id="banner">
      <img class="logo" alt="logo thumbnail" src="">
      <div class="title">
      <h1>FoundHound</h1>
      </div>
      <div class="log">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'adminpage' }" v-if="$store.state.token != ''">Admin</router-link>
      <router-link v-bind:to="{name: 'userdashboard'}"  v-if="$store.state.token != ''" >Set Criteria</router-link>
      <div class="spacer"></div>
      <a id="about" href="#">About Us</a>
    </div>
    <router-view />
  </div>
</template>

<style scoped>

</style>
<script setup lang="ts">
</script>