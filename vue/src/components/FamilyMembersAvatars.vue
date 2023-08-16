<template>
  <div class="family-avatars">
    <div
      class="member-avatar"
      v-for="member in familyMembers"
      :key="member.userId"
    >
      <img
        id="avatar"
        :src="getMemberAvatar(member.userId)"
        alt="user Avatar"
        @click="handleMemberClick(member.userId)"
      />
      <p>{{getMemberUsername(member.userId)}}</p>
    </div>
  </div>
</template>

<script>
import userService from "../services/UserService";

export default {
  name: "family-members-avatars",
  data() {
    return {
      familyMembers: [],
    };
  },
  methods: {
    async handleMemberClick(memberId) {
      const memberUsername = await this.getMemberUsername(memberId);
      this.changeMemberFilter(memberUsername);
      this.changeMemberFilterId(memberId);
    },
    async changeMemberFilter(memberUsername) {
      this.$store.commit("UPDATE_FAMILY_MEMBER_FILTER", memberUsername);
    },
    changeMemberFilterId(memberId) {
      this.$store.commit("UPDATE_FAMILY_MEMBER_FILTER_ID", memberId);
    },
    // async getMemberUsername(id) {
    //   const response = await userService.getUserById(id);
    //   return response.data.username;
    // },
    getMemberUsername(id) {
        return this.familyMembers.find(famMem => famMem.userId === id).username;
    },
    getMemberAvatar(id) {
      const memberUsername = this.getMemberUsername(id);
      return `https://api.multiavatar.com/${memberUsername}.png`;
    },
  },
  computed: {
    currentUser() {
      return this.$store.state.user;
    },
  },
  async created() {
    try {
      const familyMembersResponse = await userService.getListOfFamilyMembers(this.currentUser.familyId);
      this.familyMembers = familyMembersResponse.data;
    } catch (error) {
      console.error("Error fetching family members", error);
    }
  },
};
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
