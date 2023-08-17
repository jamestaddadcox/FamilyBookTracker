<template>
  <page-layout>
    <template v-slot:headerbar>
      <page-header class="page-header"></page-header>
    </template>

    <template v-slot:content>
      <family-book-display></family-book-display>
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

</style>
<style>

.family.sidebar {
  background-color: #df5fff;
}

.family.sidebar ul li a {
  text-decoration: none;
  font-size: 50px;
  background-color:#efafff;
  width: 70%;
  margin: 0 auto;
  margin-left: -10px;
  display: block;
  border-radius: 60px;
  padding: 10px 10px;
  position: sticky;
  font-weight: bold;
}

family.sidebar ul li {
  padding: 7px 0;
  background-color: #c4e4ff; /* Change to light blue button color */
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  margin: 10px 0;
  font-family: "Dosis";
}


.family.sidebar ul li:hover {
  background-color: #377059; /* Change to highlight color on hover */
  border-radius: 50px 0 0 50px;
  margin-left: -10px;
}
</style>