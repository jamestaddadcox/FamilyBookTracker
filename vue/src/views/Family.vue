<template>
  <page-layout>
    <template v-slot:headerbar>
      <page-header></page-header>
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

</style>