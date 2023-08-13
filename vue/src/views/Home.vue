<template>
  <page-layout>
    <template v-slot:header>
      <page-header></page-header>
    </template>

    <template v-slot:content>
      <image-uploader></image-uploader>
      <book-cover-list></book-cover-list>
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
import BookCoverList from '../components/BookCoverList.vue';



export default {
  name: "home-view",
  components: {
    PageLayout,
    SideBarMenu,
    PageHeader,
    BookCoverList,
    
  },
};
</script>
<style scoped>

</style>