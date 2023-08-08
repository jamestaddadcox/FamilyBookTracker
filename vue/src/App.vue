<template>
<body>
  

  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <router-view />

    
  </div>
  </body>
</template>

<script>

export default {
  name: "App",
  
};
</script>

<style scoped>
body {
  background-color: cyan;
}
</style>


