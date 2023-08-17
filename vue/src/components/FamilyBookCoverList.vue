<template>
  <div class="book-section">
    <book-card
      class="card"
      v-for="bookuser in filteredBookUsers"
      :key="`${bookuser.isbn}-${bookuser.userId}`"
      :bookuser="bookuser"
    ></book-card>
    
    <!-- <span class="card"><button class="add" @click="showAddBookModal">
      ADD A BOOK
    </button>
    </span> -->

    <!-- <add-book-modal v-show="isAddBookModalVisible" @close="closeAddBookModal">
    </add-book-modal> -->
  </div>
</template>

<script>
import BookCard from "../components/BookCard.vue";
import BookService from "../services/BookService";
// import UserService from "../services/UserService";
// import AddBookModal from "./AddBookModal.vue"; ///////PARENT ABILITY
export default {
  name: "family-book-cover-list",
  components: {
    BookCard,
    // AddBookModal,
  },
  data() {
    return {
        familymembers: [], //id's of all the users with the same family id.
        bookUsersInFamily: [], 
        //   isAddBookModalVisible: false,
    };
  },
  computed: {
    filteredBookUsers() {
      const activeFamilyMemberId = this.$store.state.activeFamilyMemberId;
       return this.bookUsersInFamily.filter(familyBookUser => familyBookUser.userId == activeFamilyMemberId);
    },
    currentUser() {
      return this.$store.state.user;
    },
  },
  async created() {
    this.bookUsersInFamily = (await BookService.getAllBookUserInfoByFamilyId(this.currentUser.familyId)).data;
  }
  
  
  ,
  methods: {
    // showAddBookModal() {
    //   this.isAddBookModalVisible = true;
    // },
    // closeAddBookModal() {
    //   this.isAddBookModalVisible = false;
    // },
  },
};
</script>

<style scoped>
.book-section {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  
height: 100%;
padding: 0;
padding-right: 20px;
flex-grow: 1f;
align-content: left;
  
}
.card {
  background-color: rgb(200, 240, 227);
  border: 3px solid purple;
  border-radius: 10px;
  max-width: 235px;
  min-width: 175px;
  flex-grow: 1;
  height: 350px;
  margin-top: 25px;
  margin-left: 20px;
  padding: auto;
  align-items: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-evenly;
  padding: 10px; 
}
</style>