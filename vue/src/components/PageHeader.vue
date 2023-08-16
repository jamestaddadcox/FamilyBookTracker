<template>
  <div >
    <h1 class="top-banner" v-if="thisRoute.endsWith('/')">Hi, {{$store.state.user.firstName}}!</h1>
    <h1 class="top-banner" v-if="thisRoute.endsWith('/family')">Group  {{$store.state.user.familyId}}</h1>
    <h1 class="top-banner" v-if="thisRoute.endsWith('prizes')">Prizes and Rewards</h1>
  </div>
</template>

<script>
// import UserService from '../services/UserService';

export default {
  name: 'page-header',
  computed: {
    thisRoute() {
      return this.$route.path;
    },
    
  }
}
</script>

<style scoped>
  #top-banner {
    padding-left: 50px;
  }
  
  h1 {
    font-size: 72px;
  }

  div {
    display: flex;
    flex-direction: column;
    background-color: rgb(87, 149, 59);
    justify-content: center;
    height: 100%;
    max-height: 250px;
    padding: 0px;
  }

  .top-banner{
    padding-left: 20px;
  }

</style>