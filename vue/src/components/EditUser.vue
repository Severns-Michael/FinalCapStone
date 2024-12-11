<template>
  <form class="users-form">
    <div class="form-group">
      <!-- Username Dropdown -->
      <label for="username">Username:</label>
      <select v-model="selectedUser.username" @change="getSelectedUser('username')">
        <option v-for="user in users" :key="'username-' + user.userId">
          {{ user.username }}
        </option>
      </select>

      <!-- Role Dropdown -->
      <label for="role">Role:</label>
      <select v-model="selectedUser.name" @change="getSelectedUser('role')">
        <option v-for="user in users" :key="'role-' + user.userId">
          {{ user.name }}
        </option>
      </select>

      <!-- Submit Button -->
      <button type="submit">Submit</button>
    </div>
  </form>
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