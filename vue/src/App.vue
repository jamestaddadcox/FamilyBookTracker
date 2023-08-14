<template>
  <body id="cloud-intro">
    <div id="app">
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
  background-color: #f3e3df;
  cursor: url('@/assets/lammy.gif'), auto;
}


html,
body,
.container {
  width: 100%;
  height: 100%;
  min-width: 500px;
  min-height: 500px;
  margin: 0;
  padding: 0;
}
</style>
