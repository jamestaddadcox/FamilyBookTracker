<template>
  <div class="book-section">
    <button class="add card">FUTURE ADD BOOK BUTTON</button>
    <book-card class="card" v-for="bookuser in $store.state.bookUser" :key="bookuser.isbn" :bookuser="bookuser"></book-card>
    <!-- <book-card v-for="bookuser in usersBooks($store.state.user.user_id)" :key="bookuser.isbn" :bookuser="bookuser"></book-card> -->
  </div>
</template>

<script>
import BookCard from "@/components/BookCard.vue";
import BookService from "@/services/BookService";
export default {
    name:'book-cover-list',
    components:{
         BookCard
    },
    computed:{
      usersBooks(){
        return BookService.getBookUsersByUserId(1);} //HARDCODEDTEST
    }
}
</script>

<style scoped>


.book-section {
    display: flex;
    justify-content: space-evenly;
    flex:wrap;
    background-color: pink;
}
.card {
  background-color: rgb(200, 240, 227);
  border: 3px solid purple;
  border-radius: 10px;
  width: 250px;
  height: 400px;
  margin: 10px;

  display: block;
}
</style>