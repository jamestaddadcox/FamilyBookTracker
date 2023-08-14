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
          format: "",
        },
        authorKey: "",
        workKey: "",
        radioChoice: "",  // flags: isbn, enter-data, isbnDataReturned
      };
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
        this.book.format = "";
        this.radioChoice = "";
    },
      isbnLookup(isbn) {
      bookInfoService.getBookInfoByIsbn(isbn)
      .then((result) => {
      console.log(result.data.title);
      this.radioChoice = "isbnDataReturned"
      this.book.title = result.data.title;
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
      this.close();
    }
    },


}
</script>

<style>

</style>