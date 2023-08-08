<template>
  <div class="book-section">
    <book-card v-for="book in usersBooks($store.state.user)" :key="book.isbn"></book-card>
  </div>
</template>

<script>
import BookCard from "@/components/BookCard.vue";
import BookService from "@/services/BookService";
export default {
    components:{
        BookCard
    },
    computed:{
        usersBooks(id,isbn){

        }
    }
}
</script>

<style>
.book-section {
    display: flex;
    justify-content: space-evenly;
    flex:wrap;
}
</style>