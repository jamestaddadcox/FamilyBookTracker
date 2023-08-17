<template>
  <div>
    <button class="route-user" id="avatar-home" @click="goHome" title="Go Home">
      <img
        id="avatar"
        v-if="currentUser.avatarUrl == null"
        :src="
          'https://api.multiavatar.com/' + currentUser.username + '.png'
        "
        alt="avatar"
      />
    </button>
  </div>
</template>

<script>
export default {
  name: "avatar",
  computed: {
    currentUser() {
      return this.$store.state.user;
    },
  },
  methods: {
    goHome() {
      this.$router.push("/");
    },
   
  },
};
</script>
    
<style>
#avatar-home{
  background: none;
  border: none;
  border-radius: 50%;
}
</style>