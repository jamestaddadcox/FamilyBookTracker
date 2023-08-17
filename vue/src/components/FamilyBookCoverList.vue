<template>
  <div class="book-section">
    <book-card
      class="card"
      v-for="bookuser in bookUsersInFamily"
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
  
  
   async created() {
    // try {
    //     this.familymembers = await (await UserService.getListOfFamilyMembers(this.$store.state.user.familyId)).data;
    //     console.log(this.familymembers);
    // } catch (error) {
    //     console.error("Error fetching books:", error);
    // }

    try{
        this.bookUsersInFamily = await (await BookService.getAllBookUserInfoByFamilyId(this.$store.state.user.familyId)).data;
    } catch (error) {
        console.error("Error fetching book-user stats", error);
    }

    // try {
    //   this.bookUsers = await (await BookService.getBookUsersByUserId(this.$store.state.user.userId)).data; //HARDCODED
    //   console.log(this.bookUsers);
    // } catch (error) {
    //   console.error("Error fetching books:", error);
    // }
  },
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
  background-color: #efafff;
  border: 6px solid #545454;
  font-family: "Dosis";
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