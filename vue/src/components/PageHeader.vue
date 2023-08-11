<template>
  <div >
    <h1>Hi {{$store.state.user.firstName}}</h1>
  </div>
</template>

<script>
// import UserService from '../services/UserService';

export default {
  name: 'page-header',
  computed: {

  }
}
</script>

<style scoped>
  div {
    display: flex;
    flex-direction: column;
    background-color: bisque;
    justify-content: center;
    align-items: center;
    height: 100%;
  }
</style>