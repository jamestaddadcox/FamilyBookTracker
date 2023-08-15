<template>
  <page-layout>
    <template v-slot:headerbar>
      <page-header></page-header>
      
    </template>


<template v-slot:content>
  <prize-list :familyId="userFamilyId"></prize-list>

  
  <prize-modal></prize-modal>
</template>


    <template v-slot:sidebar>
      <side-bar-menu></side-bar-menu>
    </template>
  </page-layout>
</template>

<script>
import PrizeModal from '../components/PrizeModal.vue';
import PageLayout from '../views/PageLayout.vue';
import SideBarMenu from '../components/SideBarMenu.vue';
import PageHeader from '../components/PageHeader.vue';
import PrizeList from '../components/PrizeList.vue';
import PrizeService from '../services/PrizeService';
import PrizeModal from '../components/PrizeModal.vue';


export default {
  name: "prizes-view",
  components: {
    PageLayout,
    SideBarMenu,
    PageHeader,
   PrizeList,
    PrizeModal,
   
    
  },
  data() {
    return {
      prizes: [],
      isModalOpen: false
    };
  },
  computed: {
    userFamilyId() {
      return this.$store.state.user ? this.$store.state.user.family_id : null;
    },
  },
    async created() {
    try {
      this.prizes = await PrizeService.getPrizesByFamilyId(1); //hardcoded NEED TO FIX
    } catch (error) {
      console.error("Error fetching prizes:", error);
    }
  },
  methods: {
    openModal() {
      this.isModalOpen = true;
    },
    closeModal() {
      this.isModalOpen = false;
    }
  }
};
</script>

<style scoped>
  .prize-boxes {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }

headerbar {
  background-color: #ff5757;
}  
</style>