<template>
  <div class="page-layout">
    <header><slot name="header"></slot></header>
    <main><slot name="content"></slot></main>
    <sidebar><slot name="sidebar"></slot></sidebar>
  </div>
</template>

<script>
export default {
  name: "page-layout",
};
</script>

<style scoped>
.page-layout {
  display: grid;
  grid-template-columns: 1fr 1fr 250px;
  grid-template-rows: auto;
  grid-template-areas:
    "header header sidebar"
    "main main sidebar";
  min-height: 100vh;
}

header {
  grid-area: header;
  max-height: 150px;
}

main {
  grid-area: main;
}

sidebar {
  grid-area: sidebar;
  background-color: #f4f4f4;
  padding: 0px;
  border-left: 1px solid #ddd;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 100%;
}
</style>
