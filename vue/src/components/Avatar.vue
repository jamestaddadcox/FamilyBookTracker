<template>
  <div>
    <img
      id="avatar"
      v-if="currentUser.avatarUrl == null"
      v-bind:src="
        'https://api.multiavatar.com/' + currentUser.username + '.png'
      "
      alt="elise avatar"
    />
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
};
</script>
    
<style>

</style>