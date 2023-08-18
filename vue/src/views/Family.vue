<template>
  <page-layout>
    <template v-slot:headerbar>
      <page-header class="page-header"></page-header>
    </template>

    <template v-slot:content>
      <div class="scrollable-content">
      <family-book-display></family-book-display>
      </div>
    </template>

    <template v-slot:sidebar>
      <side-bar-menu></side-bar-menu>
    </template>

  </page-layout>
</template>

<script>

import PageLayout from '../views/PageLayout.vue';
import SideBarMenu from '../components/SideBarMenu.vue';
import PageHeader from '../components/PageHeader.vue';
import FamilyBookDisplay from '../components/FamilyBookDisplay.vue';
//import BookCoverList from '../components/BookCoverList.vue';
//import FamilyBookCoverList from '../components/FamilyBookCoverList.vue';



export default {
  name: "family-view",
  components: {
    PageLayout,
    SideBarMenu,
    PageHeader,
    FamilyBookDisplay,
    //BookCoverList,
    //FamilyBookCoverList,
  },
};
</script>

<style scoped>
.page-header {
  background-color: #d11bff;
}

.scrollable-content {
  height: calc(100vh - 200px);
  overflow-y: scroll;
}

</style>
<style>

.family.sidebar {
  background-color: #df5fff;
}

.family.sidebar ul li a {
  text-decoration: none;
  font-size: 50px;
  
  width: 70%;
  margin: 0 auto;
  margin-left: 60px;
  display: block;
  border-radius: 50px 0 0 50px;
  margin-left: 30px;
  padding: 10px 10px;
  position: sticky;
  font-weight: bold;
}

.family.sidebar ul li {
  padding: 7px 0;
  background-color: #efafff; /* Change to light blue button color */
  margin: 10px 0;
  font-family: "Dosis";
}


.family.sidebar ul li:hover {
  background-color: #ff26ed; /* Change to highlight color on hover */
  
}
</style>