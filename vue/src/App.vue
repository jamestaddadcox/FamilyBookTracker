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
  cursor: url(https://cur.cursors-4u.net/nature/nat-10/nat994.cur), auto !important;
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
