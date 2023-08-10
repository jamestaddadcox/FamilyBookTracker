<template>
  <div class="book-section">
    HARDCO
    <book-card
      v-for="bookuser in bookUsersByUserId(1)"
      :key="bookuser.isbn" :bookuser="bookuser"
    ></book-card>
  </div>
</template>

<script>
import BookCard from "@/components/BookCard.vue";
import BookService from "@/services/BookService";
export default {
  data(){
    return{
      tempId: '',
    };
  },
  name: "book-cover-list",
  components: {
    BookCard,
  },
  computed: {
    usersBooksByFamilyId(id){
      return BookService.getBookUsersByFamilyID(id);
    },
    bookUsersByUserId(id){
      return BookService.getBookUsersByUserId(id);
    },
  },
  // methods: {
  //   getBookByIsbn(isbn){

  //   }
  // }
}
</script>

<style>
.book-section {
  display: flex;
  justify-content: space-evenly;
  flex: wrap;
  
}
</style>