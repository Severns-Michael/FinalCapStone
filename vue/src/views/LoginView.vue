<template>
  <div id="login" class="text-center">
    <div class="form-container">
      <h1>Please Sign In</h1>
      <form v-on:submit.prevent="login">
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <button class="btn purple-btn" type="submit">Sign In</button>
        <p>
          <router-link class="login-link" v-bind:to="{ name: 'register' }">
            Need an account? Sign up.
          </router-link>
        </p>
      </form>
    </div>
  </div>
</template>


<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
.form-container {
  background-color: var(--purp4); 
  padding: 30px; 
  border-radius: 10px; 
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); 
  max-width: 400px; 
  margin: 110px auto; 
  color: white;
  text-align: center; 
}

h1 {
  font-size: 2em; 
  font-weight: bold;
  margin-bottom: 20px;
}

.form-input-group {
  display: flex;
  flex-direction: column; 
  align-items: flex-start; 
  margin-bottom: 1rem; 
  width: 100%; 
  max-width: 300px; 
  margin: 0 auto;
}

label {
  font-size: 1em;
  margin-bottom: 5px;
  color: white; 
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid var(--purp3);
  border-radius: 5px;
  box-sizing: border-box;
  font-size: 1em;
}

button.purple-btn {
  padding: 10px 30px;
  font-size: 1.2em; 
  color: white;
  background-color: var(--purp1);
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
  margin-top: 10px;
}

button.purple-btn:hover {
  background-color: var(--purp4);
  transform: scale(1.05);
}

.login-link {
  font-size: 1.1em;
  font-weight: bold;
  color: white;
  text-decoration: none;
  transition: color 0.2s ease;
  margin-top: 10px;
  display: inline-block;
}

.login-link:hover {
  color: var(--purp1);
  text-decoration: underline;
}

</style>