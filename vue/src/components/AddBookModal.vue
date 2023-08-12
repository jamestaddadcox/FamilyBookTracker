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
          <slot name="radio-input">

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
import bookInfoService from '../services/BookInfoService.js'

export default {
    name: "AddBookModal",
    data() {
      return {
        book: {
          title: "",
          authorId: "",
          author: "",
          isbn: "",
        },
        radioChoice: "",
      };
    },
    methods: {
      close() {
      this.clearForm();
      this.$emit("close");
    },
    clearForm() {
        // this.user.username = "";
        // this.user.password = "";
    },
    isbnLookup(isbn) {
      bookInfoService.getBookInfoByIsbn(isbn)
      .then((result) => {
      console.log(result.data.title);
      this.book.title = result.data.title;
      this.book.isbn = isbn;
});
    },
    },


}
</script>

<style>

</style>