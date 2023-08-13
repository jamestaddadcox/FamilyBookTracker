<template>
  <div class="sidebar">    

    //put image uploader here for now.
  <div id="sidebar-avatar">
    <file-upload></file-upload>
  </div>

    <menu class="link-list">
      <ul class="active-sidebar-menu">
        <li v-if="!$route.path.endsWith('/family')">
          <router-link
            v-bind:to="{ name: 'family' }"
            v-if="$store.state.token != ''"
            >Family Page</router-link
          >
        </li>
        <li v-if="!$route.path.endsWith('/prizes')">
          <router-link
            v-bind:to="{ name: 'prizes' }"
            v-if="$store.state.token != ''"
            >Prizes</router-link
          >
        </li>
        <li v-if="!$route.path.endsWith('/')">
          <router-link
            v-bind:to="{ name: 'home' }"
            v-if="$store.state.token != ''"
            >Home</router-link
          >
        </li>
        <li v-if="!$route.path.endsWith('/settings')">
          <router-link
            v-bind:to="{ name: 'settings' }"
            v-if="$store.state.token != ''"
            >Settings</router-link
          >
        </li>
        <li>
          <router-link
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
            >Log Out</router-link
          >
        </li>
      </ul>
    </menu>
  </div>
</template>

<script>
import FileUpload from "@/components/FileUpload.vue";

export default {
  name: "sidebar-menu",
  components: {
    FileUpload,
  },
  computed: {
    currentUser() {
      return this.$store.state.user;
    },
  },
};
</script>

<style>
.sidebar {
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: coral;
  padding-right: 0;
  padding-top: 0;
  padding-bottom: 0;
  margin: 0;
}

.sidebar ul {
  list-style: none;
  padding: 0;
}

.sidebar ul li {
  padding: 13px 0;
  background-color: rgba(255, 255, 0, 0.6);
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
  margin: 10px 0;
}

.sidebar ul li a {
  text-decoration: none;
  font-size: 1.2rem;
  color: black;
  width: 70%;
  margin: 0 auto;
  display: block;
  border-radius: 20px 0 0 20px;
  padding: 10px 10px;
  position: sticky;
}

.sidebar ul li:hover {
  background-color: rgba(255, 255, 0, 0.777);
  border-radius: 20px 0 0 20px;
  margin-left: -10px;
}

#avatar {
  
  
  justify-self: center;
  display: flex;
  outline: solid 3px black;
  border-radius: 50%;
  height: 125px;
  width: 125px;
}
#avatar:hover {
  outline: solid 5px lime;
  border-radius: 50%;

}
#sidebar-avatar {
  padding-top: 10%;
  display: flex;
  justify-content: center;
  justify-self: center;
}
</style>