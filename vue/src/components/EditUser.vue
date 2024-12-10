<!--<template>-->
<!--  <form class="users-form">-->
<!--    <div class="form-group">-->
<!--      <label for="username">Username:</label>-->
<!--      <select v-model:="selectedUser.username" @change="getSelectedUser">-->
<!--        <option v-for="user in users" v-bind:key="user.userId">{{user.username}}</option>-->
<!--      </select>-->
<!--        <label for="role">Role:</label>-->
<!--      <select  v-model="selectedUser.name" @change="getSelectedUser">-->
<!--        <option v-for="user in users" v-bind:key="user.userId">{{user.name}}</option>-->
<!--      </select>-->
<!--      <button type="submit">Submit</button>-->

<!--    </div>-->
<!--  </form>-->

<template>
  <div>
    hello
  </div>
</template>

<script>
import UserService from "../services/UserService";

export default {
  data() {
    return {
      users: [],
      selectedUser: {},
      user : {
        username: '',
        role: ''
      }

    }
  },
  created() {
    UserService.getUsers().then(response => {
      this.users = response.data;
    });

  },
  methods: {
    getUsers() {
      UserService.getUsers().then(response => {
        this.users = response.data;
      });
    },
    getSelectedUser() {
      this.users.find(user => {
        if (user.username === this.selectedUser.username) {
          this.selectedUser = user;
        }
      });
    },


  },
}

</script>

<style scoped>

</style>