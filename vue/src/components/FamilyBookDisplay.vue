<template>
  <div>
    <div class="avatar-box">
      <div></div>
      <p v-for="id in familyMembersIds" :key="id">
        {{ id }}
      </p>
      <family-members-avatars></family-members-avatars>

      <family-members-avatars
        class="avatar"
        v-for="id in familyMembersIds"
        :key="id"
        :whichMemberId="id"
      ></family-members-avatars>
    </div>


    <div class="filtered-family-book-list"></div>
      <family-book-cover-list></family-book-cover-list>
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
      this.familyMembersIds = await UserService.getListOfFamilyMembers(this.$store)
       
      .data.then
        console.log(this.familyMembersIds);      
    } catch (error) {
      console.error("Error fetching family member ids");
    }
    
    
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