<template>
<body id="cloud-intro">
  

  <div id="app">
    <!-- We're no longer using this nav: 
      <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>-->
    <router-view />

    
  </div>
  </body>
</template>

<script>

export default {
  
  name: "App",
  
};
</script>

<style>
body {
  background-color: rgb(34, 193, 255);
  
}
html, body, .container{
  width: 100%;
  height: 100%;
  min-width: 500px;
  min-height: 500px;
  margin: 0;
  padding: 0;
 
}
body{
  background-color: #007ced;
	background: linear-gradient(to bottom, #7bd7ff 1%,#cce7ff 100%);
}
#cloud-intro{
  position: relative;
  height: 100%;
  background: url(https://static.radulescu.me/examples/clouds/clouds1000.png);
  background: url(https://static.radulescu.me/examples/clouds/clouds1000.png) 0 200px,
              url(https://static.radulescu.me/examples/clouds/clouds1200_1.png) 0 300px,
              url(https://static.radulescu.me/examples/clouds/clouds1000_blur3.png) 100px 250px;
	animation: wind 50s linear infinite;
}
@keyframes wind{
  0% {
    background-position: 0 200px, 0 300px, 100px 250px;
  }
  100% {
    background-position: 1000px 200px, 1200px 300px, 1100px 250px;
  }

}

</style>


