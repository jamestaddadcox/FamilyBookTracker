<template>
  <div class="page-layout">
    <div class="box headerbar"><slot name="headerbar"></slot></div>
    <div class="box content"><slot name="content"></slot></div>
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
  grid-template-columns: 2fr 2fr 1fr;
  grid-template-rows: 200px 1fr;
  grid-template-areas:
    "headerbar sidebar"
    "data sidebar";
  min-height: 100vh;
  background-color: #f3e3df;;
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
  background-color: #f4f4f4;
  padding: 0px;
 
  display: flex;
  flex-direction: column;
  justify-content: space-between;
<<<<<<< HEAD
  
=======
  border: 3ch solid #5235359f; /* Border color of the section */
  border-width: 1px; /* beveled effect */
  border-radius: 2px; /*rounded corners */
  box-shadow: 3px 2px 5px rgba(0, 0, 0, 0.2);
  background-color: palevioletred; 
>>>>>>> 6a3caad177736e6662b477c1ab63206f576bdf08
  
  
}
</style>
