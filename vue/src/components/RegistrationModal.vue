<template>
  <transition name="modal-fade">
    <div class="modal-backdrop">
      <div class="modal">
        <header class="modal-header">
          <slot name="header"> Welcome, New Book Worm! </slot>
          <button type="button" class="btn-close" @click="close">
            &times;
          </button>
        </header>

        <section class="modal-body">
          <slot name="body">
            <form @submit.prevent="register">
              <div role="alert" class="alertmsg" v-if="registrationErrors">
                {{ registrationErrorMsg }}
              </div>
              <div class="form-group">
                <label for="username">Username:</label>
                <input
                  type="text"
                  id="username"
                  v-model="user.username"
                  required
                  autofocus
                />
              </div>
              <div class="form-group">
                <label for="firstName">First Name:</label>
                <input
                  type="text"
                  id="firstName"
                  v-model="user.firstName"
                  required
                />
              </div>
              <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input
                  type="text"
                  id="lastName"
                  v-model="user.lastName"
                  required
                />
              </div>
              <div class="form-group">
                <label for="familyName">Family or Group Name:</label>
                <input
                  type="text"
                  id="familyName"
                  v-model="user.familyName"
                  required
                />
              </div>
              <div class="form-group">
                <label for="password">Password:</label>
                <input
                  type="password"
                  id="password"
                  v-model="user.password"
                  required
                />
              </div>
              <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input
                  type="password"
                  id="confirmPassword"
                  v-model="user.confirmPassword"
                  required
                />
              </div>
              <button type="submit" class="btn-green">Let's Go!</button>
            </form>
          </slot>
        </section>
      </div>
    </div>
  </transition>
</template>


<script>
import authService from "../services/AuthService";

export default {
  name: "RegistrationModal",
  data() {
    return {
      user: {
        username: "",
        firstName: "",
        lastName: "",
        familyName: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    close() {
      this.clearForm();
      this.$emit("close");
    },
    clearForm() {
        this.user.username = "";
        this.user.firstName = "";
        this.user.lastName = "";
        this.user.familyName = "";
        this.user.password = "";
        this.user.confirmPassword = "";
        this.user.role = "user";
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.login()
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
  },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  
};
</script>

<style scoped>
.modal-backdrop {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal-body {
  font-family: "Dosis";
  font-size: 1.5rem;
}
.modal {
  width: 80%;
  max-width: 500px;
  background-color: white;
  padding: 20px;
  border-radius: 4px;
  text-align: left;
  position: fixed; /* Change position to fixed */
  top: 50%; /* Center vertically */
  left: 50%; /* Center horizontally */
  transform: translate(-50%, -50%); /* Adjust to center perfectly */
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
  font-size: 25px; /* Adjust the font size as needed */
}

.btn-close {
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
  color: #555;
}

.form-group {
  margin-bottom: 15px;
  display: flex;
  flex-direction: column;
}

.form-group label {
  margin-bottom: 5px;
}

/* Adjust the input box height and font size */
.form-group input {
  height: 40px; /* Adjust as needed */
  font-size: 16px; /* Adjust as needed */
}

</style>