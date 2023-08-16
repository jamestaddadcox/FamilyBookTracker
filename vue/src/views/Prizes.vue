<template>
  <page-layout>
    <template v-slot:headerbar>
      <page-header>
        
      </page-header>
      
    </template>


<template v-slot:content>
      <div class="scrollable-content">
        <prize-modal class="prize-modal"></prize-modal>
        <prize-list :familyId="userFamilyId"></prize-list>
        
      </div>
    </template>

 <template v-slot:sidebar>
      <div class="sidebar-wrapper">
        <side-bar-menu></side-bar-menu>
      </div>
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
      this.prizes = await PrizeService.getPrizesByFamilyId(this.$store.state.user.family_id); //hardcoded NEED TO FIX
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
/* Style for the scrollable content */
.scrollable-content {
  height: calc(100vh - 150px); /* Adjust the height as needed */
  overflow-y: auto;
}

/* Style for the headerbar */
headerbar {
  background-color: #ff5757;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
}

/* Offset the content below the fixed headerbar */
.content {
  margin-top: 150px; /* Adjust the margin as needed */
}

headerbar {
  background-color: #ff5757;
}  
.sidebar-wrapper {
  overflow-y: auto;
}



</style>