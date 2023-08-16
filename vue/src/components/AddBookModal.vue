<template>
  <transition name="modal-fade">
    <div class="modal-backdrop">
      <div class="modal">
        <header class="modal-header">
          <slot name="header">Add a Book</slot>
          <button type="button" class="btn-close" @click="close">
            &times;
          </button>
        </header>

        <section class="modal-body">
          <slot name="radio-input" v-if="radioChoice != 'isbnDataReturned'">

            <input type="radio" id="by-isbn" name="add" value="isbn" v-model="radioChoice" checked>
            <label for="by-isbn">look up by isbn</label><br>
            <input type="radio" id="enter-data" name="add" value="enter-data" v-model="radioChoice">
            <label for="enter-data">enter data</label><br>

          </slot>

          <div id="enter-isbn" v-show="radioChoice === 'isbn'">
            <label for="isbn-box">isbn</label>
            <input type="text" id="isbn-box" v-model="book.isbn">
            <button type="button" class="btn-green" @click="isbnLookup(book.isbn)">find book!</button>

          </div>

          <div id="book-data-display" v-show="radioChoice === 'isbnDataReturned'">
            <div>title: {{book.title}}</div>
            <div>author: {{book.author}}</div>
            <div>isbn: {{book.isbn}}</div>
            <div>{{book.description}}</div>
            <button type="button" class="btn-green" v-show="!userDataFlag" @click="enterUserData">this looks right!</button>
          </div>

          <div id="enter-user-data" v-show="userDataFlag">
            <div>
            <label for="format">what format are you reading this book in?</label>
            </div>
            <div>
            <select name="format" id="format-select" v-model="bookUser.format">
              <option value="paper">paper</option>
              <option value="digital">digital</option>
              <option value="audio">audiobook</option>
              <option value="readaloud-reader">read-aloud: reader</option>
              <option value="readaloud-listener">read-aloud: listener</option>
            </select>
            </div>
            <div>
            <label for="completed">i have already finished this book</label>
            <input type="checkbox" name="completed" id="completed" v-model="bookUser.completed">
            </div>
            <div>
              <label for="pages-read"># of pages read:</label>
              <input type="number" name="pages-read" id="pages-read" v-model="bookUser.pagesRead">
            </div>
            <div>
              <label for="minutes-read">total reading time so far, in minutes:</label>
              <input type="number" name="minutes-read" id="minutes-read" v-model="bookUser.minutesRead">
            </div>
            <div>
              <label for="notes">notes:</label>
            </div>
            <div>
              <textarea name="notes" id="notes" cols="30" rows="4" v-model="bookUser.notes"></textarea>
            </div>

            <button type="button" class="btn-green" @click="addBook(book)">add book!</button>

          </div>

          <div id="enter-book-data" v-show="radioChoice === 'enter-data'">
            <label for="title">title</label>
            <input type="text" id="title" v-model="book.title"><br>
            <label for="author">author(s)</label>
            <input type="text" id="author" v-model="book.author"><br>
            <label for="isbn-manual">isbn</label>
            <input type="text" id="isbn-manual" v-model="book.isbn"><br>
            <button type="submit" class="btn-green">add book!</button>

          </div>
        
        </section>

      </div>
    </div>
  </transition>
</template>


<script>
import bookInfoService from '../services/BookInfoService.js';
import bookService from '../services/BookService.js';

export default {
    name: "AddBookModal",
    data() {
      return {
        book: {
          title: "",
          author: "",
          isbn: "",
          description: "",
          pages: "",
        },
        bookUser: {
          userId: "",
          isbn: "",
          minutesRead: "",
          notes: "",
          completed: false,
          format: "",
          pagesRead: ""

        },
        authorKey: "",
        workKey: "",
        radioChoice: "",  // flags: isbn, enter-data, isbnDataReturned
        userDataFlag: false
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
        this.book.title = "";
        this.authorKey = "";
        this.workKey = "";
        this.book.author = "";
        this.book.isbn = "";
        this.book.description = "";
        this.book.pages = "",
        this.radioChoice = "";
        this.bookUser.userId = "";
        this.bookUser.isbn = "";
        this.bookUser.minutesRead = "";
        this.bookUser.notes = "";
        this.bookUser.completed = "";
        this.bookUser.format = "";
        this.bookUser.pagesRead = "";
        this.userDataFlag = false;
    },
      enterUserData() {
        this.userDataFlag = true;
        this.bookUser.isbn = this.book.isbn;
        this.bookUser.userId = this.$store.state.user.userId;
      },
      isbnLookup(isbn) {
      bookInfoService.getBookInfoByIsbn(isbn)
      .then((result) => {
      console.log(result.data.title);
      console.log(result.data.number_of_pages);
      this.radioChoice = "isbnDataReturned";
      this.book.title = result.data.title;
      this.book.pages = result.data.number_of_pages;
      this.authorKey = result.data.authors[0].key;
      this.workKey = result.data.works[0].key;
      this.book.isbn = isbn;
      bookInfoService.getAuthorByAuthorKey(this.authorKey)
      .then((authorResult) => {
        this.book.author = authorResult.data.name;
      });
      bookInfoService.getDescriptionByWorkKey(this.workKey)
      .then((workResult) => {
        console.log(workResult.data.description.value);
        this.book.description = workResult.data.description.value;
      })
      });
    },
    addBook(book) {
      bookService.addBook(book);
      bookService.addBookForCurrentUser(this.bookUser);
      this.close();
    }
    },


}
</script>

<style>

</style>