<template>
<div class="card">
    <span>can you see this?</span>
    <h2 class="title">{{ this.book.title }}HARDCODEDTITLE</h2>
    <img class="cover" v-bind:src="'http://covers.openlibrary.org/b/isbn/' + this.book.isbn + '-L.jpg'" alt="book cover image">
    <h3 class="author">{{ this.book.author }}</h3>
    <h3 class="mins-read">{{ bookuser.minsRead }}</h3>
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
    created() {
        this.getBookByIsbn(this.bookuser.isbn)
            .then(response => {
                this.book = response.data; 
            })
            .catch(error => {
                console.error('Error fetching book:', error);
            });
    },
    methods: {
        getBookByIsbn(isbn) {
            return BookService.getBookByIsbn(isbn);
        }
    }
}
</script>
<style scoped>

</style>