<template>
  <div class="book-card card">
    <div id="isbn-box" class="box"><h5 class="card-isbn">isbn: {{ book.isbn }}</h5></div>
    <button id="cover-box" class="box" @click="showEditBookModal">
      <!-- <img class="blank-image" src="../assets/noImageFound.png"> -->
      <img
        class="cover-image"
        :src="
          'http://covers.openlibrary.org/b/isbn/' + bookuser.isbn + '-M.jpg'
        "
        onerror="this.src='../assets/TestAvatar.png"
        alt="book cover image"
      />
    </button>
    <div id="title-box" class="box">
      <h3 class="title" v-if="book.title">{{ book.title }}</h3>
    </div>
    <div id="author-box" class="box">
        <h4 class="author">{{book.author}}</h4></div>
    <div id="minutes-box" class="box">
      <h5 class="minutes" v-if="bookuser.minutesRead">Mins read: {{ bookuser.minutesRead }}</h5>
    </div>
    <div id="pages-box" class="box" >
      <h5 class="pages" v-if="bookuser.pagesRead">
        {{ bookuser.pagesRead }} of {{ book.pages }} pages
      </h5>
    </div>
    <div id="timer-box" class="box">
    <button class="timer">Start Reading Clock</button></div>
    <edit-book-modal v-show="isEditBookModalVisible" @close="closeEditBookModal"></edit-book-modal>
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
  methods: {
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
    flex-grow: 1;
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
  max-height: 110px;
  margin-top: 10px;
  grid-area: "cover";
  width: 10 px;
  border: none;
  background: none;
  justify-self: center;
}
.title {
  grid-area: "title";
  justify-self: center;
  align-self: center;
  margin: 5px px 5px 0px;
  padding: 0px;
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