<template>
  <transition name="modal-fade">
    <div class="modal-backdrop">
      <div class="modal">
        <header class="modal-header">
          <slot name="header"><h2>Edit Book Stats</h2></slot>
          <button type="button" class="btn-close" @click="close">
            &times;
          </button>
        </header>

        <section class="modal-body">
          <div id="book-data-display">
            <div><b>title:</b> {{ book.title }}</div>
            <div><b>author:</b> {{ book.author }}</div>
            <div><b>isbn:</b> {{ book.isbn }}</div>
            <div>{{ book.description }}</div>
          </div>

          <div id="enter-user-data">
            <div class="form-group">
              <label for="format">what format are you reading this book in?</label>
              <select name="format" id="format-select" v-model="updatedBookUser.format">
                <option value="paper">paper</option>
                <option value="digital">digital</option>
                <option value="audio">audiobook</option>
                <option value="readaloud-reader">read-aloud: reader</option>
                <option value="readaloud-listener">read-aloud: listener</option>
              </select>
            </div>
            <div class="form-group">
              <label for="completed">have you finished this book?</label>
              <input type="checkbox" name="completed" id="completed" v-model="updatedBookUser.completed">
            </div>
            <div class="form-group">
              <label for="pages-read"># of pages read:</label>
              <input type="number" name="pages-read" id="pages-read" v-model="updatedBookUser.pagesRead">
            </div>
            <div class="form-group">
              <label for="minutes-read">total reading time so far, in minutes:</label>
              <input type="number" name="minutes-read" id="minutes-read" v-model="updatedBookUser.minutesRead">
            </div>
            <div class="form-group">
              <label for="notes">notes:</label>
              <textarea name="notes" id="notes" cols="30" rows="4" v-model="updatedBookUser.notes"></textarea>
            </div>

            <button type="button" class="btn-green" @click="updateBookUser">update book!</button>
          </div>
        </section>
      </div>
    </div>
  </transition>
</template>

<script>
import bookService from '../services/BookService.js';

export default {
  name: 'EditBookModal',
  props: {
    book: Object,
    bookuser: Object,
  },
  data() {
    return {
      updatedBookUser: {
        userId: this.bookuser.userId,
        isbn: this.bookuser.isbn,
        minutesRead: this.bookuser.minutesRead,
        notes: this.bookuser.notes,
        completed: this.bookuser.completed,
        format: this.bookuser.format,
        pagesRead: this.bookuser.pagesRead,
      },
    };
  },

  computed: {},

  methods: {
    close() {
      this.clearForm();
      this.$emit('close');
    },
    clearForm() {
      this.updatedBookUser.userId = this.bookuser.userId;
      this.updatedBookUser.isbn = this.bookuser.isbn;
      this.updatedBookUser.minutesRead = this.bookuser.minutesRead;
      this.updatedBookUser.notes = this.bookuser.notes;
      this.updatedBookUser.completed = this.bookuser.completed;
      this.updatedBookUser.format = this.bookuser.format;
      this.updatedBookUser.pagesRead = this.bookuser.pagesRead;
    },
    updateBookUser() {
      bookService.updateBookUser(this.updatedBookUser, this.bookuser.userId, this.bookuser.isbn);
      this.close();
    },
  },
};
</script>

<style scoped>
.modal-backdrop {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-body {
  font-family: 'Dosis';
  font-size: 1.5rem;
}

.modal {
  display: flex;
  width: 80%;
  max-width: 500px;
  background-color: white;
  padding: 20px;
  border-radius: 4px;
  text-align: left;
  position: fixed; /* Change position to fixed */
  top: 50%; /* Center vertically */
  left: 50%; /* Center horizontally */
  transform: translate(-50%, -50%); /* Adjust to center perfectly */
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
  font-size: 25px; /* Adjust the font size as needed */
}

.btn-close {
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
  color: #555;
}

.form-group {
  margin-bottom: 15px;
  display: flex;
  flex-direction: column;
}

.form-group label {
  margin-bottom: 5px;
}

.form-group input[type='checkbox'] {
  margin-top: 5px;
}

/* Adjust the input box height and font size */
.form-group input,
.form-group select,
.form-group textarea {
  height: 40px; /* Adjust as needed */
  font-size: 16px; /* Adjust as needed */
}

#book-data-display {
  text-align: center; /* Center text within book data display */
}
</style>
