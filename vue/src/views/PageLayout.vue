<template>
  <div class="page-layout">
    <div class="box headerbar"><slot name="headerbar"></slot></div>
    <div class="scrollable-content">
    <div class="box content"><slot name="content"></slot></div>
    </div>
    <div class="box sidebar"><slot name="sidebar"></slot></div>
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
  grid-template-columns: 2fr 350px;
  grid-template-rows: 200px 0.5fr;
  grid-template-areas:
    "headerbar sidebar"
    "data sidebar";
  min-height: 100vh;
  background-color: #f3e3df;;
  overflow: hidden;
}

div.headerbar {
  grid-area: headerbar;
  
 
  background-color: #ff5757;
  color: #545454;
}

div.data {
  grid-area: data;
  
}

div.sidebar {
  grid-area: sidebar;
  background-color: #af0000;
  padding: 0px;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background-color: #ff66c4;
}
.scrollable-content {
  height: calc(100vh - 150px);
  overflow-y: auto;
}  
  

</style>
