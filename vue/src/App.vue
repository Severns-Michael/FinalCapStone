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
    <div id="nav" v-if="$store.state.token != ''">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'adminpage' }" v-if="$store.state.user.authorities[0].name == 'ROLE_ADMIN'">Admin</router-link>
      <router-link v-bind:to="{name: 'userdashboard'}"  v-if="$store.state.user.authorities[0].name == 'ROLE_USER'" >Dashboard</router-link>
      <router-link v-bind:to="{name: 'setcriteria'}" v-if="$store.state.user.authorities[0].name == 'ROLE_USER'"> Set Preferences </router-link>
      <router-link :to="{name: 'swipingview'}" v-if="$store.state.user.authorities[0].name == 'ROLE_USER'">Look at Dogs!</router-link>
      <router-link :to="{name: 'Testview'}" >Test Swiper</router-link>

      <a>About Us</a>
    </div>
    <router-view />
  </div>
</template>

<style scoped>

</style>
<script>
</script>