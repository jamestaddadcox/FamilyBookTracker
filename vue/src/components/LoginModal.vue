<template>
  <transition name="modal-fade">
    <div class="modal-backdrop">
      <div class="modal">
        <header class="modal-header">
          <slot name="header"> Welcome Back, Book Worm! </slot>
          <button type="button" class="btn-close" @click="close">
            &times;
          </button>
        </header>

        <section class="modal-body">
          <slot name="body">
            <form @submit.prevent="login">
              <div role="alert" v-if="invalidCredentials">
                Invalid username and password!
              </div>
              <div role="alert" v-if="credentialsNotFound">
                I'm sorry, we appear to be experiencing techincal difficulties...
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
                <label for="password">password</label>
                <input
                  type="password"
                  id="password"
                  v-model="user.password"
                  required
                />
              </div>
              <button type="submit" class="btn-green">sign in!</button>
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
  name: "LoginBox",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
      credentialsNotFound: false,
    };
  },
  methods: {
    close() {
      this.$emit("close");
    },
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          } else {
            this.credentialsNotFound = true;
          }
        });
    },
  },
};
</script>

<style>
</style>