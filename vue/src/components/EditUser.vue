<template>
  <form class="users-form">
    <div class="form-group">
      <!-- Username Dropdown -->
      <label for="username">Username:</label>
      <select v-model="selectedUser.username" @change="getSelectedUser">
        <option v-for="user in users" :key="user.id" :value="user.username">
          {{ user.username }}
        </option>
      </select>
<!--      &lt;!&ndash; Role Dropdown &ndash;&gt;-->
<!--      <label for="role">Role:</label>-->
<!--      <select v-model="selectedUser.name" @change="getSelectedUser('role')">-->
<!--        <option v-for="user in users" :key="'role-' + user.userId">-->
<!--          {{ user.authorities[0].name }}-->
<!--        </option>-->
<!--      </select>-->
      <div>
        <h6>Role: </h6>
        <input id="User" type="radio" value="ROLE_USER" v-model="selectedRole" />
        <label for="User"> user </label>

        <input id="Admin" type="radio" value="ROLE_ADMIN" v-model="selectedRole" />
        <label for="Admin"> admin </label>
      </div>

      <!-- Submit Button -->
      <button type="submit" :disabled="!selectedUser.username || !selectedRole" @click.prevent="updateUser">
        Submit
      </button>
    </div>
  </form>
</template>

<script>
import UserService from "../services/UserService";
import DogService from "@/services/DogService";

export default {
  data() {
    return {
      users: [
        {
          id: 2,
          username: "admin",
          authorities: [
            {
              name: "ROLE_ADMIN",
            },
          ],
          yesTraits: [
            {
              traitId: 0,
              traitName: null,
            },
          ],
          noTraits: [
            {
              traitId: 0,
              traitName: null,
            },
          ],
        },
      ],
      selectedUser: {},
      user : {
        username: '',
        role: null
      },
      selectedRole: null,

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
      // Look for a user with a matching username
      const user = this.users.find(user => user.username === this.selectedUser.username);

      if (user) {
        // Populate the entire selectedUser object with the matching user data
        this.selectedUser = {...user}; // Spread to avoid Vue reactivity issues
        console.log("Selected User:", this.selectedUser); // Debug logging
      } else {
        console.error("User not found in the list!"); // Error case
        this.selectedUser = {
          username: "", // Reset to default state
          id: null,
          authorities: [],
        };
      }
    },

    updatePromotedUser(user) {
      UserService.promote(user).then(response => {
        this.user = response.data;
      });
    },
    updateDemotedUser(user) {
      UserService.demote(user).then(response => {
        this.user = response.data;
      })
    },
    updateRole(role) {
      this.selectedRole = role;

      // Update `selectedUser.authorities`
      if (this.selectedUser && this.selectedUser.authorities) {
        this.selectedUser.authorities[0].name = role;
      } else {
        console.error("No user selected or authorities missing.");
      }
    },
    updateUser() {
      console.log("Selected User:", this.selectedUser);
      console.log("Payload:", {
        id: this.selectedUser.id,
        username: this.selectedUser.username,
      });

      // Ensure the user is selected
      if (!this.selectedUser || !this.selectedUser.id) {
        alert("No user selected. Please select a user first.");
        return;
      }

      // Ensure a role is selected
      if (!this.selectedRole) {
        alert("No role selected. Please select a role.");
        return;
      }

      // Call the proper API
      const apiCall = this.selectedRole === "ROLE_ADMIN" ? UserService.promote : UserService.demote;

      apiCall({
        id: this.selectedUser.id,
        username: this.selectedUser.username,
      })
          .then(response => {
            console.log("API Response:", response.data); // Debugging
            this.user = response.data;                 // Update
            alert("User role updated successfully!");
          }).catch(error => {
        console.error("Full API Error:", error); // Log the complete error object

        if (error.response) {
          // Server responded with a non-2xx status code
          console.error("Response Error Data:", error.response.data);
          console.error("Response Status:", error.response.status);
          console.error("Response Headers:", error.response.headers);
        } else if (error.request) {
          // No response was received from the server
          console.error("No Response Received:", error.request);
        } else {
          // There was an issue setting up the request
          console.error("Request Error:", error.message);
        }

        alert("Failed to update user. Please try again.");
      });
    }




  },
}

</script>

<style scoped>

</style>