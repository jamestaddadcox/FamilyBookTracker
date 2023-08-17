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
  padding: 0px;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

div.sidebar ul li a {
  text-decoration: none;
  font-size: 50px;
  color: #545454;
  width: 70%;
  margin: 0 auto;
  margin-left: 60px;
  display: block;
  border-radius: 60px;
  padding: 10px 10px;
  position: sticky;
  font-weight: bold;
}

div.sidebar ul li:hover {
 
  border-radius: 50px 0 0 50px;
  margin-left: -10px;
  background-color:red;
}


</style>

<style>
div.sidebar ul li:hover {
 
  border-radius: 50px 0 0 50px;
  margin-left: -10px;
  background-color: rgb(222, 255, 172);
}

div.sidebar ul li a {
  text-decoration: none;
  font-size: 50px;
  color: #545454;
  width: 70%;
  margin: 0 auto;
  margin-left: 60px;
  display: block;
  border-radius: 60px;
  padding: 10px 10px;
  position: sticky;
  font-weight: bold;
}

</style>
