<template class="prize-view">
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
        <side-bar-menu class="sidebar"></side-bar-menu>
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
      return this.$store.state.user ? this.$store.state.user.prizes_id : null;
    },
  },
    async created() {
    try {
      this.prizes = await PrizeService.getPrizesByFamilyId(this.$store.state.user.prizes_id); //hardcoded NEED TO FIX
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
  height: calc(100vh - 200px); /* Adjust the height as needed */
  overflow-y: scroll;
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


.prize-view {
  width: 100vw;
  height: 100vh;
  margin: auto auto;
}

.prize-modal {
  margin-top: 46px;
  padding: auto;
  align-items: center;
  width: 12vw;
  margin-left: 67.5vw;
  position: -webkit-sticky
}




</style>

<style >

.prize.sidebar {
  grid-area: sidebar;
  padding: 0px;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background-color: #ff66c4;
}
.prize.sidebar ul li:hover {
  border-radius: 50px 0 0 50px;
  margin-left: -10px;
  background-color: rgb(255, 226, 63);
}

.prize.sidebar ul li {
  background-color: #ffc5e8;
  padding: 7px 0;
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  margin: 10px 0;
  font-family: "Dosis";
}
.prize.sidebar ul li a {
  text-decoration: none;
  font-size: 50px;
  color: #545454;
  width: 70%;
  margin: 0 auto;
  margin-left: 60px;
  display: block;
  border-radius: 50px 0 0 50px;
  margin-left: 30px;
  padding: 10px 10px;
  position: sticky;
  font-weight: bold;
  
}


.prize .sidebar ul li:hover {
  background-color: red;
}



</style>