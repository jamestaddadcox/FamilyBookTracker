<template>
  <div >
    <h1 class="top-banner" v-if="thisRoute.endsWith('/')">Hi, {{$store.state.user.firstName}}!</h1>
    <h1 class="top-banner" v-if="thisRoute.endsWith('/family')">Group Id: {{$store.state.user.familyId}}</h1>
    <h1 class="top-banner" v-if="thisRoute.endsWith('prize')">PRIZES</h1>
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
    font-size: 100px;
    font-family: "Limelight";
    text-align: center;
  }

  div {
    display: flex;
    flex-direction: column;
    background-color: #ff5757;
    justify-content: center;
    height: 200px;
    max-height: 300px;
    padding: 0px;
  }

  .top-banner{
    padding-left: 20px;
  }

</style>