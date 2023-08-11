<template>
  <div class="book-card">
    <span class="card">can you see this?</span>
    <button>
    <img class="cover" v-bind:src="'http://covers.openlibrary.org/b/isbn/' + bookuser.isbn + '-M.jpg'" alt="book cover image">
    </button>
    <h2 class="title" v-if="book.title">{{ book.title }}</h2>
    <div v-else>Loading...</div>
    <button>Start Reading Clock</button>
  </div>
</template>

<script>
import BookService from "@/services/BookService";
export default {
    name: 'book-card',
    props: ['bookuser'],
    data() {
        return {
            book: {} 
        };
    },
    async created() {
        try {
            const response = await this.getBookByIsbn(this.bookuser.isbn);
            this.book = response.data; 
        } catch (error) {
            console.error('Error fetching book:', error);
        }
    },
    methods: {
        getBookByIsbn(isbn) {
            return BookService.getBookByIsbn(isbn);
        }
    }
}
</script>
<style scoped>
/* .card {
    display: flex;
    flex-direction: column;
    border: 3px;
    border-color: aqua;
} */
</style>