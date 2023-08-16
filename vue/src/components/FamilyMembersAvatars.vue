<template>
  <!-- <div
     :class="{chosen:$store.state.activeFamilyMemberId === whichMember }"
    :title="`Click to show this person's books`"
    @click="filterByMember(whichMemberId)"> -->
    <div class="family-avatars">
    <div class="member-avatar" v-for="username in familyUsernames" :key="username"><img id="avatar"
       
        :src="
          'https://api.multiavatar.com/' + username + '.png'
        " alt="" @click="changeMemberFilter(username)"/>
    </div>
    </div>
    <!-- </div> -->
</template>

<script>
import userService from '../services/UserService';

export default {
    name: "family-members-avatars",
    props: {
       
    },
    data() {
        return {
            familyUsernames: [],
        }
    },
    methods: {
        changeMemberFilter(memberUsername) {
            this.$store.commit('UPDATE_FAMILY_MEMBER_FILTER', memberUsername);
        },
    },
    computed: {
        currentUser() {
            return this.$store.state.user;
        }
    },
    created() {
        userService.getListOfFamilyMembers(this.currentUser.familyId).then(response => {
            this.familyUsernames = response.data.map(user => user.username);
        });

        // userService.getListOfFamilyMembers(this.currentUser.familyId).then(response => {
        //     this.familyMemberId = $store.
        // })
    }

}
</script>

<style scoped>
.family-avatars {
    display: flex;
    padding: 20px 20px 0px 20px;
    margin-left: 25px;
}

.member-avatar {
    padding: 10px;
}

#avatar {
    width: 75px;
    height: 75px;
}
</style>