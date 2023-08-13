<template>
  <div class="book-section">
    <button class="add card" id="add-book" @click="showAddBookModal">FUTURE ADD BOOK BUTTON</button>
    <book-card
      class="card"
      v-for="bookuser in bookUsers"
      :key="bookuser.isbn"
      :bookuser="bookuser"
    ></book-card>

    <!-- <book-card v-for="bookuser in usersBooks($store.state.user.user_id)" :key="bookuser.isbn" :bookuser="bookuser"></book-card> -->
    <add-book-modal
     v-show="isAddBookModalVisible"
      @close="closeAddBookModal"
      > 
    </add-book-modal>
  </div>
</template>

<script>
import BookCard from "@/components/BookCard.vue";
import BookService from "@/services/BookService";
import AddBookModal from './AddBookModal.vue';
export default {
  name: "book-cover-list",
  components: {
    BookCard,
    AddBookModal,
  },
  data() {
    return {
      bookUsers: [],
      isAddBookModalVisible: false,
    };
  },
  async created() {
    try {
      this.bookUsers = await BookService.getBookUsersByUserId(2); //HARDCODED
      console.log(this.bookUsers);
    } catch (error) {
      console.error("Error fetching books:", error);
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
.book-section {
  display: flex;
  justify-content: space-evenly;
  flex: wrap;
  background-color: pink;
}
.card {
  background-color: rgb(200, 240, 227);
  border: 3px solid purple;
  border-radius: 10px;
  width: 250px;
  height: 400px;
  margin: 10px;

  display: block;
}
</style>