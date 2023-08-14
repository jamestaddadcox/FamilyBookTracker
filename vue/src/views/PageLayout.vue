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
  grid-template-rows: 150px 1fr;
  grid-template-areas:
    "headerbar headerbar sidebar"
    "data data sidebar";
  min-height: 100vh;
}

headerbar {
  grid-area: headerbar;
  border: 3ch solid #5235359f; 
  border-width: 1px; 
  border-radius: 2px; 
  box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.2); 
}

data {
  grid-area: data;
}

sidebar {
  grid-area: sidebar;
  background-color: #f4f4f4;
  padding: 0px;
  border-left: 4px solid #4d2323b6;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  border: 3ch solid #5235359f; /* Border color of the section */
  border-width: 1px; /* beveled effect */
  border-radius: 2px; /*rounded corners */
  box-shadow: 3px 2px 5px rgba(0, 0, 0, 0.2); 
  
  
}
</style>
