<template>
  <div id="register" class="text-center">
    <div class="form-container">
      <h1>Create Account</h1>
      <form v-on:submit.prevent="register">
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">Confirm Password</label>
          <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
        </div>
        <button type="submit" class="btn purple-btn">Create Account</button>
        <p>
          <router-link v-bind:to="{ name: 'login' }" class="login-link">
            Already have an account? Log in.
          </router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  color:white;
  margin-right: 0.5rem;
}
.purple-btn {
  padding: 10px 30px;
  font-size: 1em;
  color: white;
  background-color: var(--purp1); 
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.purple-btn:hover {
  background-color: var(--purp4);
  transform: scale(1.05); 
}
.login-link {
  font-size: 1.1em;
  font-weight: bold; 
  color: white; 
  text-decoration: none;
  transition: color 0.2s ease;
}

.login-link:hover {
  color: var(--purp1); 
  text-decoration: underline; 
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
  margin-top: 10px; 
}
.login-link {
  margin-top: 6px; 
  display: inline-block; 
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
  color: white;
}
</style>
