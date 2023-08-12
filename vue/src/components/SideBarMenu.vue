<template>
  <div class="sidebar">
    <div class="side-avatar"><img id="side-avatar" src="https://api.multiavatar.com/elise.png" alt="elise avatar"></div>
    <menu class="link-list">
      <ul>
        <li class="active-sidebar-menu">
          <router-link v-bind:to="{ name: 'family' }">Family</router-link>
        </li>
        <li>
          <a href="#">Stats</a>
        </li>
        <li>
          <a href="#">Add-Progress</a>
        </li>
        <li>
          <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        </li>
      </ul>
      <div class="logout-settings">
        <div class="logout-btn"> <!--feel free to use something other than div & this class here, just wanted to separate these two links -->
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">logout</router-link>
        </div>
        <div class="settings-btn">
        <a href="#">settings</a>
        </div>
      </div>
    </menu>
  </div>
</template>

<script>
export default {
  name: "sidebar-menu"
}
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

#side-avatar {
  width: 45%;
  justify-self: center;
  display: flex;
  padding-left: 25%;
  padding-right: 25%;
  padding-top: 10%;
}

</style>