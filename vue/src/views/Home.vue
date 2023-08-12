<template>
  <div>
  <page-layout>
    <template v-slot:header>
      <page-header></page-header>
    </template>

    <template v-slot:content>
      <book-cover-list></book-cover-list>
    </template>

    <template v-slot:sidebar>
      <side-bar-menu></side-bar-menu>
    </template>

  </page-layout>
  <!-- <add-book-modal
      v-show="isAddBookModalVisible"
      @close="closeAddBookModal"
      > 
    </add-book-modal> -->
  </div>
</template>

<script>

import PageLayout from '../views/PageLayout.vue';
import SideBarMenu from '../components/SideBarMenu.vue';
import PageHeader from '../components/PageHeader.vue';
import BookCoverList from '../components/BookCoverList.vue';
// import AddBookModal from '../components/AddBookModal.vue';



export default {
  name: "home-view",
  components: {
    PageLayout,
    SideBarMenu,
    PageHeader,
    BookCoverList,
    // AddBookModal
  },
  data() {
      return {
        isAddBookModalVisible: true,
      }
    },
    methods: {
      showAddBookModal() {
            this.isAddBookModalVisible = true;
        },
      closeAddBookModal() {
            this.isAddBookModalVisible = false;
        }
    }
};
</script>
<style scoped>

</style>