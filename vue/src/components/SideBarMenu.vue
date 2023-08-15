<template>
  <div class="sidebar">    

    
  <div id="sidebar-avatar">
   
    <!-- <file-upload></file-upload> -->
        <avatar></avatar>
        <span><button 
        class="change-avatar"  @Click="showUpdateAvatarModal">&plus;</button></span>
        
  </div>


    <upload-avatar-modal v-show="isUpdateAvatarModalVisible"
    @close="closeUpdateAvatarModal">
    </upload-avatar-modal>


    <menu class="link-list">
      <ul class="active-sidebar-menu">
        <li v-if="!$route.path.endsWith('/family')">
          <router-link
            v-bind:to="{ name: 'family' }"
            v-if="$store.state.token != ''"
            >Family</router-link
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
import Avatar from './Avatar.vue';
// import FileUpload from "./FileUpload.vue";
import UploadAvatarModal from './UploadAvatarModal.vue';


export default {
  data (){
    return {
      isUpdateAvatarModalVisible: false,
    };
  },
  name: "sidebar-menu",
  components: {
    // FileUpload,
    UploadAvatarModal,
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
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: #ff66c4;
  padding-right: 0;
  padding-top: 0;
  padding-bottom: 0;
  margin-right: 0px;
  position: sticky;
  width: 100%;
}

.sidebar ul {
  list-style: none;
  padding: 0;
}

.sidebar ul li {
  padding: 7px 0;
  background-color: #ffc5e8;
  border-top-left-radius: 30px;
  border-bottom-left-radius: 30px;
  margin: 10px 0;
  border: 5ch solid #545454;
  border-width: 0px;
  box-shadow: 3px 2px 5px rgba(0, 0, 0, 0.2); 
  font-family: "Dosis";
  
}

.sidebar ul li a {
  text-decoration: none;
  font-size: 4rem;
  color: #545454;
  width: 70%;
  margin: 0 auto;
  display: block;
  border-radius: 40px 0 0 40px;
  padding: 10px 10px;
  position: sticky;
}

.sidebar ul li:hover {
  background-color: rgba(255, 255, 0, 0.777);
  border-radius: 30px 0 0 30px;
  margin-left: -10px;
}

#avatar {
  
  margin-top: 5px;
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
  flex-direction: column;
  align-items: center;
  justify-content: center;
  justify-self: center;
  font-family: "Limelight";
}

#sidebar-avatar h2 {
  font-family: "Limelight";
}

</style>