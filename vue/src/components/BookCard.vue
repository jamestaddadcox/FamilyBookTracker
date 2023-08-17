<template>
  <div class="book-card">
    <h5 class="isbn">isbn: {{ book.isbn }}</h5>
    <div class="cover-box" @click="isEditBookModalVisible = true">
      <img
        class="cover-image"
        :src="getCoverImageSrc()"
        alt="book cover image"
      />
    </div>
    <div class="card-content">
      <h3 class="title">{{ book.title }}</h3>
      <div class="author">{{ book.author }}</div>
      <div class="minutes" v-if="bookuser.minutesRead">Mins read: {{ bookuser.minutesRead }}</div>
      <div class="pages" v-if="bookuser.pagesRead">
        {{ bookuser.pagesRead }} of {{ book.pages }} pages
      </div>
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
    getCoverImageSrc() {
      if (this.bookuser.isbn) {
        const coverImageUrl =
          "http://covers.openlibrary.org/b/isbn/" + this.bookuser.isbn + "-L.jpg";
        const placeholderImageUrl = require("@/assets/gold.png");
        return coverImageUrl || placeholderImageUrl;
      } else {
        return require("@/assets/gold.png");
      }
    },
  },
};
</script>
<style scoped>
.book-card{

  font-family: "Dosis";
  border-radius: 10px;
  max-width: 335px; /* Increase the max-width */
  height: 400px; /* Increase the height */
  margin: 25px 20px;
  padding: 60px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  text-align: center;
}
.isbn {
  flex-grow: 0; /* Take up the least amount of space */
  margin-bottom: 5px; /* Remove margin to save space */
  font-size: 15px; /* Adjust font size if needed */
}

.cover-box {
  flex-grow: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0; /* Remove padding */
  margin: 0; /* Remove margin */
  border: none;
  background: none;
}

.cover-image {
  width: 260px; /* Set the desired width */
  height: 370px; /* Set the desired height */
  object-fit: cover; /* Preserve aspect ratio and cover the entire space */
  box-shadow: 5px 7px 15px rgb(87, 105, 92);
  border-radius: 10px;
}


.card-content {
  flex-grow: 1;
  display: flex;
  position:-webkit-sticky;
  flex-direction: column;
  justify-content: space-between;
  align-self: center;
  margin-top: 0px; /* Reduce top margin */
}

.title {
  font-family: "Dosis";
  padding-top: 10px;
  margin: 5px 0;
  padding: 10;
  word-wrap: break-word;
  line-height: 1.3;
  font-size: 18px;
  font-weight: bold;
}

.author {
  font-size: 16px;
  margin-top: 5px;
  font-weight: 400;
}

.minutes,
.pages {
  font-size: 14px;
  margin-top: 5px;
  font-weight: 300;
}

.edit-book-button {
  margin-top: 10px;
}
</style>
