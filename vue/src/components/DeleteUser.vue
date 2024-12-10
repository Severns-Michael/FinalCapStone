<template>
  <div class="container">
    <div>
      <label>User: </label>
      <select v-model="this.selectedUser.username" v-on:click="getUsers()">
        <option v-for="user in users" v-bind:key="user.userId" v-bind="this.selectedUser.username">
          {{ user.username }}
        </option>
      </select>
    </div>
    <button v-on:click.prevent="deleteUser" class="btn btn-light">Delete User</button>
  </div>
</template>

<script >
import UserService from "@/services/UserService";

export default {
  data() {
    return {
      users:[],
      selectedUser: {},
    }
  },
  created() {
    this.getUsers().then(response => {
      this.users = response.data;
    });
  },
  methods: {
    getUsers() {
      UserService.getUsers().then(response => {
        this.users = response.data;
      })
    },
  deleteUser() {
    this.users.find(user =>{
     if(user.username === this.selectedUser.username){
       this.selectedUser = user;
     }
    })
    UserService.deleteUser(this.selectedUser.id).then(response => {
      this.selectedUser = {};
      UserService.getUsers().then(response => {
        this.users = response.data;
      })
    }).catch(error => {
      console.log(error);
    })

  }
}
}

</script>

<style scoped>

</style>