<template>
<div class="card">
    <h2 class="title">{{book.title}}</h2>
    <img class="cover" v-bind:src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'" alt="book cover image">
    <h3 class="author">{{book.author}}</h3>
    <h3 class="mins-read">{{books.minsRead}}</h3>
</div>
</template>

<script>
export default {
    name: 'book-card',
    props: ['book'],

}
</script>

<style>

</style>