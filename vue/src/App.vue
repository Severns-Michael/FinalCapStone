<template>
  <div id="capstone-app">
    <div id="banner">
      <div class="logo">
      <img alt="logo thumbnail" src="../src/assets/logo-full.png">
      </div>
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
      <router-link v-bind:to="{name: 'userdashboard'}"  v-if="$store.state.token != ''" >Dashboard</router-link>
      <router-link v-bind:to="{name: 'setcriteria'}"> Set Preferences </router-link>
      <router-link :to="{name: 'swipingview'}">Look at Dogs!</router-link>
      <router-link :to="{name: 'Testview'}">Test Swiper</router-link>
      <a>About Us</a>
    </div>
    <router-view />
  </div>
</template>

<style scoped>

</style>
<script>
</script>