<template>
  <div>
    <div class="avatar-box">
      <div></div>
      <p>paragrso</p>
      <p v-for="username in FamilyMembersUsernames" :key="username">
        {{ username }}
      </p>
      <family-members-avatars
        class="avatar"
        v-for="member in familyMembers"
        :key="member.username"
        :whichMember="member"
      ></family-members-avatars>
    </div>
    <div class="filtered-family-book-list"></div>
      <family-book-cover-list></family-book-cover-list>
      <button>testbutton</button>
    <div class="filtered-book-lists"></div>
  </div>
</template>

<script>
import UserService from "../services/UserService";
import FamilyBookCoverList from "../components/FamilyBookCoverList.vue";
import FamilyMembersAvatars from "../components/FamilyMembersAvatars.vue";

export default {
  name: "family-book-display",
  components: {
    FamilyMembersAvatars,
    FamilyBookCoverList,
  },
  props: {},
  data() {
    return {
      familyMembersIds: [],
      familyMembers: [],
    };
  },
  async created() {
    try {
      this.familyMembersIds = await UserService.getListOfFamilyMembers(
        this.$store.state.user.familyId
      ).data;
        console.log(this.familyMembers);      
    } catch (error) {
      console.error("Error fetching family member ids");
    }
    
    this.familyMembersUsernames = this.familyMembersIds.map(
        (userId) => this.changeIdToUsername(userId).data.username
      );
  },
  methods: {
    changeIdToUsername(id) {
      let theUser = null;
      try {
        theUser = UserService.getUserById(id);
      } catch (error) {
        console.error("Error fetching family members list");
      }
      return theUser;
    },
  },
};
</script>

<style>
.avatar-box {
  display: flex;
  flex-direction: column;
}
</style>