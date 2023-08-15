<template>
  <div
    :class="{chosen: $store.state.activeFamilyMemberId === whichMember }"
    :title="`Click to show this person's books`"
    @click="filterByMember(whichMember)">
    <span class="member"><img id="avatar"
        v-if="currentUser.avatarUrl == null"
        :src="
          'https://api.multiavatar.com/' + whichMember.username + '.png'
        " alt=""></span>
    
    </div>
</template>

<script>
export default {
    name: "family-members-avatars",
    props: {
        whichMember :{
            //could list required, type, validator
        }
    },
    methods: {
        filterByMember(whichMember){
            this.$store.commit('UPDATE_FAMILY_MEMBER_FILTER',whichMember);
        }
    }

}
</script>

<style>

</style>