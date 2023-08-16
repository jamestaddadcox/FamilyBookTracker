<template>
  <div class="page-layout">
    <headerbar class="box"><slot name="headerbar"></slot></headerbar>
    <data class="box"><slot name="content"></slot></data>
    <sidebar class="box"><slot name="sidebar"></slot></sidebar>
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
  grid-template-columns: 2fr 2fr 1fr;
  grid-template-rows: 200px 0.5fr;
  grid-template-areas:
    "headerbar headerbar sidebar"
    "data data sidebar";
  min-height: 100vh;
  background-color: #f3e3df;;
  overflow: hidden;
}

headerbar {
  grid-area: headerbar;
  
 
  background-color: #ff5757;
  color: #545454;
}

data {
  grid-area: data;
  
}

sidebar {
  grid-area: sidebar;
  background-color: #af0000;
  padding: 0px;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  
  
  
}
</style>
