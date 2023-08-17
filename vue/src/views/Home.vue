<template>
  
    
  <page-layout>
    <template v-slot:headerbar>
      <page-header class="page-header"></page-header>
    </template>

    <template v-slot:content>
      <div class="scrollable-content">
      <image-uploader></image-uploader>
      <book-cover-list></book-cover-list>
      </div>
    </template>

    <template v-slot:sidebar>
      <side-bar-menu class="sidebar"></side-bar-menu>
    </template>

  </page-layout>
  <!-- <add-book-modal
      v-show="isAddBookModalVisible"
      @close="closeAddBookModal"
      > 
    </add-book-modal> -->
  
</template>

<script>
import PageLayout from "../views/PageLayout.vue";
import SideBarMenu from "../components/SideBarMenu.vue";
import PageHeader from "../components/PageHeader.vue";
import BookCoverList from "../components/BookCoverList.vue";
// import AddBookModal from '../components/AddBookModal.vue';

export default {
  name: "home-view",
  components: {
    PageLayout,
    SideBarMenu,
    PageHeader,
    BookCoverList,
  },
  data() {
    return {};
  },
};
</script>
<style scoped>

.scrollable-content {
  
  height: calc(100vh - 200px);
  overflow-y: scroll;
  
}

.page-header {
  background-color: #0cc0df;
}


.home.sidebar {
  background-color: #5ce1e6;
}

.home.sidebar ul li {
  padding: 7px 0;
  background-color: #c4e4ff; /* Change to light blue button color */
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  margin: 10px 0;
  font-family: "Dosis";
}


.home.sidebar ul li a {
  text-decoration: none;
  font-size: 50px;
  background-color:red;
  width: 70%;
  margin: 0 auto;
  margin-left: 60px;
  display: block;
  border-radius: 60px;
  padding: 10px 10px;
  position: sticky;
  font-weight: bold;
}


.home.sidebar ul li:hover {
  background-color: #377059; /* Change to highlight color on hover */
  border-radius: 50px 0 0 50px;
  margin-left: -10px;
}


</style>

<style >

.home.sidebar ul li a {
  text-decoration: none;
  font-size: 50px;
  color: #545454;
  width: 70%;
  margin: 0 auto;
  margin-left: 60px;
  display: block;
  border-radius: 60px;
  margin-left: 30px;
  padding: 10px 10px;
  position: sticky;
  font-weight: bold;
  
}

.home.sidebar ul li {
  padding: 7px 0;
  background-color: #c4e4ff; /* Change to light blue button color */
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  margin: 10px 0;
  font-family: "Dosis";
}

</style>