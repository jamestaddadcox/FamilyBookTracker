<template>
  <div class="home">
    
    <header><page-header></page-header></header>
    <div class="main-section">HARDCODEDTEST<book-cover-list></book-cover-list></div>
    <sidebar-menu class="side-bar"></sidebar-menu>
    <nav class="about-bar">about bar for now</nav>
    <!-- <p>You must be authenticated to see this</p> -->
  </div>
</template>

<script>
 import SidebarMenu from '../components/SideBarMenu.vue';
 import PageHeader from '../components/PageHeader.vue';
 import BookCoverList from '../components/BookCoverList.vue';

export default {
  components: {
    SidebarMenu,
    PageHeader,
    BookCoverList
  },
  name: "home"
};
</script>

<style>
div.home {
  display: grid;
  grid-template-columns: 1fr 1fr 250px;
  grid-template-rows: 200px 100% 25px;
  grid-template-areas:
    "header header sidebar"
    "main main sidebar"
    "faq faq sidebar";
  min-height: 100vh;
}

header {
  grid-area: header;
}

.main-section {
  grid-area: main;
  background-color: palegreen;
  height: 100%;
}

.side-bar {
  grid-area: sidebar;
}

.about-bar {
  grid-area: faq;
  background-color: cornflowerblue;
}


</style>