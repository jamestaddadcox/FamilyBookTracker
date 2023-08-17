<template>
  <div class="book-card card">
    <div id="isbn-box" class="box"><h5 class="card-isbn">isbn: {{ book.isbn }}</h5></div>
    <button id="cover-box" class="box" @click="isEditBookModalVisible=true">
      <!-- <img class="blank-image" src="../assets/noImageFound.png"> -->
      <img
        class="cover-image"
        :src="'http://covers.openlibrary.org/b/isbn/' + bookuser.isbn + '-M.jpg'"
        onerror="this.src='../assets/TestAvatar.png'"
        alt="book cover image"
      />
    </button>
    <div class="box" id="title-box">
      <h3 class="title" v-if="book.title">{{ book.title }}</h3>
    </div>
    <div class="box" id="author-box">
      <h4 class="author">{{ book.author }}</h4>
    </div>
    <div class="box" id="minutes-box">
      <h5 class="minutes" v-if="bookuser.minutesRead">Mins read: {{ bookuser.minutesRead }}</h5>
    </div>
    <div class="box" id="pages-box">
      <h5 class="pages" v-if="bookuser.pagesRead">
        {{ bookuser.pagesRead }} of {{ book.pages }} pages
      </h5>
    </div>
    <edit-book-modal v-show="isEditBookModalVisible" @close="closeEditBookModal" v-bind:book="book" v-bind:bookuser="bookuser"></edit-book-modal>
  </div>
</template>

<script>
import BookService from "../services/BookService";
import EditBookModal from './EditBookModal.vue';

export default {
  components: {
    EditBookModal,
  },
  name: "BookCard",
  props: ["bookuser"],
  data() {
    return {
      book: {},
      isEditBookModalVisible: false,
    };
  },
  async created() {
    try {
      const response = await this.getBookByIsbn(this.bookuser.isbn);
      this.book = response.data;
    } catch (error) {
      console.error("Error fetching book:", error);
    }
  },
  methods: { ///EDIT THIS TO HAVE Try
    getBookByIsbn(isbn) {
      return BookService.getBookByIsbn(isbn);
    },
    showEditBookModal() {
      this.isEditBookModalVisible = true;
    },
    closeEditBookModal() {
      this.isEditBookModalVisible = false;
    },
  },
  computed: {
    whichBookCover() {
      //we need some way to display a generic cover
      return null;
    },
  },
};
</script>
<style scoped>
.card {
  background-color: #c4e4ff;
  border: 6px solid #545454;
  font-family: "Dosis";
  border-radius: 10px;
  max-width: 235px;
  min-width: 175px;
  height: 350px;
  margin: 25px 20px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.isbn, .title, .author, .minutes, .pages {
  overflow: hidden;
  white-space: nowrap; /* Prevent text from breaking to a new line */
  text-overflow: ellipsis; /* Display ellipsis (...) for overflowed text */
}

#isbn-box{
    flex-grow: 1;
}

#title-box {
    flex-grow: 1;
}

#author-box {
    flex-grow: 1;
}

#cover-box {
    flex-grow: 2;
    
}

#minutes-box {
    flex-grow: 1;
}

#pages-box {
    flex-grow: 1;
}

#timer-box {
    flex-grow: 1;
}

.card-isbn {
  grid-area: isbn;
  justify-self: center;
  font-size: 12px;
}
#cover-box {
  max-height: 100%;
  margin-top: 10px;
  grid-area: "cover";
  width: 10 px;
  border: none;
  background: none;
  justify-self: center;
}
.title {
  font-family: "Dosis";
  margin: 5px 0;
  padding: 0;
  word-wrap: break-word;
  line-height: 1.3;
}

.minutes {
  padding: 0px;
}

.timer {
  grid-area: "timer";
}

.pages {
  grid-area: "pages";
}

.blank-image,
.cover-image {
  width: 75px;
  min-height: 100px;
  max-width: 125px;
  box-shadow: 5px 7px 15px rgb(87, 105, 92);
  border-radius: 8px;
  position: sticky;
  top: 0;
  left: 0;
}



/* .blank-image {
  z-index: 1;
}

.cover-image {
  z-index: 1;
} */

div{
    overflow: hidden;
   
}

.box{
    padding: 0px;
    margin: 0px;
}



</style>