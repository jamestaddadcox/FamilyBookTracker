<template>
  <div class="sidebar">    

    
  <div id="sidebar-avatar">
   
    <!-- <file-upload></file-upload> -->
        <avatar></avatar>
        <!-- <span><button 
        class="change-avatar"  @Click="showUpdateAvatarModal">&plus;</button></span> -->
        
  </div>

        <menu class="link-list">
      <ul class="active-sidebar-menu">
        <li :class="{ active: $route.path.endsWith('/family') }">
          <router-link
            v-bind:to="{ name: 'family' }"
            v-if="$store.state.token != ''"
          >Family</router-link>
        </li>
        <li :class="{ active: $route.path.endsWith('/prize') }">
          <router-link
            v-bind:to="{ name: 'prize' }"
            v-if="$store.state.token != ''"
          >Prizes</router-link>
        </li>
        <li :class="{ active: $route.path.endsWith('/') }">
          <router-link
            v-bind:to="{ name: 'home' }"
            v-if="$store.state.token != ''"
          >Home</router-link>
        </li>
        <li :class="{ active: $route.path.endsWith('/settings') }">
          <router-link
            v-bind:to="{ name: 'settings' }"
            v-if="$store.state.token != ''"
          >Settings</router-link>
        </li>
        <li>
          <router-link
            v-bind:to="{ name: 'logout' }"
            deactivate="$store.state.token != ''"
          >Log Out</router-link>
        </li>
      </ul>
    </menu>

  </div>
</template>

<script>
import Avatar from './Avatar.vue';

// import FileUpload from "./FileUpload.vue";
// import UploadAvatarModal from './UploadAvatarModal.vue';


export default {
  data (){
    return {
      isUpdateAvatarModalVisible: false,
    };
  },
  name: "side-bar-menu",
  components: {
    // FileUpload,
    // UploadAvatarModal,
    Avatar,
  
  },
  computed: {
    currentUser() {
      return this.$store.state.user;
    },
  },
  methods: {
    showUpdateAvatarModal() {
      this.isUpdateAvatarModalVisible = true;
    },
    closeUpdateAvatarModal() {
      this.isUpdateAvatarVisible = false;
    },
  },
};
</script>

<style>
menu{
  margin-left: -15px;
}


ul{
  display: flex;
  flex-direction: column;
}
.sidebar {
  height: 110vh;
  display: flex;
  flex-direction: column;
  background-color: #ff66c4;
  padding-right: 0;
  padding-top: 0;
  padding-bottom: 0;
  margin-right: 0px;

  position:sticky;

  /* position: sticky; */

  width: 100%;

}

.sidebar ul {
  list-style: none;
  padding: 0;
}

.sidebar ul li {
  padding: 7px 0;
  background-color: #ffc5e8;
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  margin: 10px 0;
  font-family: "Dosis";
  
}

menu.linked-list{
  position: sticky;
}

.sidebar ul li a {
  text-decoration: none;
  font-size: 50px;
  color: #545454;
  width: 70%;
  margin: 0 auto;
  margin-left: 60px;
  display: block;
  border-radius: 60px;
  padding: 10px 10px;
  position: sticky;
  font-weight: bold;
}

.sidebar ul li:hover {
  background-color: rgba(255, 255, 0, 0.777);
  border-radius: 50px 0 0 50px;
  margin-left: -10px;
}

#avatar {

  margin-top: 5px;
  justify-self: center;
  display: flex;
  outline: solid 3px black;
  border-radius: 50%;
  height: 150px;
  width: 150px;
}
#avatar:hover {
  outline: solid 5px lime;
  border-radius: 50%;

}
#sidebar-avatar {
  padding-top: 10%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  justify-self: center;
  font-family: "Limelight";
}

#sidebar-avatar h2 {
  font-family: "Limelight";
}

.active {
  background-color: rgb(26, 121, 245);
  border-radius: 30px 0 0 30px;

}

</style>