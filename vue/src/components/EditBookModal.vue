<template>
  <transition name="modal-fade">
    <div class="modal-backdrop">
      <div class="modal">
        <header class="modal-header">
          <slot name="header">Edit Book Stats</slot>
          <button type="button" class="btn-close" @click="close">
            &times;
          </button>
        </header>

        <section class="modal-body">

          <div id="book-data-display">
            <div>title: {{book.title}}</div>
            <div>author: {{book.author}}</div>
            <div>isbn: {{book.isbn}}</div>
            <div>{{book.description}}</div>
          </div>

          <div id="enter-user-data">
            <div>
            <label for="format">what format are you reading this book in?</label>
            </div>
            <div>
            <select name="format" id="format-select" v-model="updatedBookUser.format">
              <option value="paper">paper</option>
              <option value="digital">digital</option>
              <option value="audio">audiobook</option>
              <option value="readaloud-reader">read-aloud: reader</option>
              <option value="readaloud-listener">read-aloud: listener</option>
            </select>
            </div>
            <div>
            <label for="completed">have you finished this book?</label>
            <input type="checkbox" name="completed" id="completed" v-model="updatedBookUser.completed">
            </div>
            <div>
              <label for="pages-read"># of pages read:</label>
              <input type="number" name="pages-read" id="pages-read" v-model="updatedBookUser.pagesRead">
            </div>
            <div>
              <label for="minutes-read">total reading time so far, in minutes:</label>
              <input type="number" name="minutes-read" id="minutes-read" v-model="updatedBookUser.minutesRead">
            </div>
            <div>
              <label for="notes">notes:</label>
            </div>
            <div>
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
    name: "EditBookModal",
    props: { 
      book : Object,
      bookuser : Object,
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
    
    computed: {
    },
    methods: {
      close() {
      this.clearForm();
      this.$emit("close");
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
    }
    },


}
</script>

<style>

</style>