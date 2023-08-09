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
              <div role="alert" v-if="registrationErrors">
                {{ registrationErrorMsg }}
              </div>
              <div>
                <label for="username">username</label>
                <input
                  type="text"
                  id="username"
                  v-model="user.username"
                  required
                  autofocus
                />
              </div>
              <div>
                <label for="firstName">first name</label>
                <input
                  type="text"
                  id="firstName"
                  v-model="user.firstName"
                  required
                />
              </div>
              <div>
                <label for="lastName">last name</label>
                <input
                  type="text"
                  id="lastName"
                  v-model="user.lastName"
                  required
                />
              </div>
              <div>
                <label for="familyName">family or group name</label>
                <input
                  type="text"
                  id="familyName"
                  v-model="user.familyName"
                  required
                />
              </div>
              <div>
                <label for="password">password</label>
                <input
                  type="password"
                  id="password"
                  v-model="user.password"
                  required
                />
              </div>
              <div class="form-input-group">
                <label for="confirmPassword">confirm password</label>
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
      this.$emit("close");
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
</style>