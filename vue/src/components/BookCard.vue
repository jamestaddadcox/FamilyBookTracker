<template>
  <div class="book-card">
    <span class="card">isbn {{book.isbn}}</span>
    <button id="cover-button">
    <img class="cover" :src="'http://covers.openlibrary.org/b/isbn/' + bookuser.isbn + '-M.jpg'" 
    onerror="this.src='../assets/TestAvatar.png"
    alt="book cover image">
    </button>
    <h2 class="title" v-if="book.title">{{ book.title }}</h2>
    <h5 class="mins-read" v-if="bookuser.minutesRead">Mins read: {{bookuser.minutesRead}}</h5>
    <h5 class="pages-read" v-if="bookuser.pagesRead">{{bookuser.pagesRead}} of {{book.pages}} pages</h5>
    <button>Start Reading Clock</button>
  </div>
</template>

<script>
import BookService from "../services/BookService";
export default {
    name: 'BookCard',
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
    },
    computed: {
        whichBookCover(){ //we need some way to display a generic cover
            return null;
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

#cover-button{
    width: 10 px;
    border: none; 
    background: none;
    
}
.cover{
    flex-grow: 1fr;
    max-width: 125px;
    box-shadow: 5px 7px 15px rgb(87, 105, 92);
    border-radius: 8px;
}
</style>