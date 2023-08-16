<template>
  <div class="prize-section">
  
    <prize-box
    class="prize-box"
    v-for="prize in sortedPrizes"
    :key="prize.prizeId"
    :prize="prize"
    @toggle-milestone="updateMilestone"
    
    ></prize-box>
    
    
 </div>
</template>

<script>
import PrizeBox from "../components/PrizeBox.vue";
import PrizeService from "../services/PrizeService";


export default {
  name: "prize-list",
  components: {
    PrizeBox,
    
  },
  data() {
    return {
      prizes: [],
      isPrizeModalVisible: false,
    };
  },
  created() {
    try {
        PrizeService
            .getPrizesByFamilyId(this.$store.state.user.familyId)
            .then((response) => { 
                this.prizes = response.data
            }); // hardcode store.state.familyId
    } catch (error) {
      console.error("Error fetching prizes:", error);
    }
  },
  methods: {
   
    showPrizeModal() {
      this.isPrizeModalVisible = true;
    },
    closePrizeModal() {
      this.isPrizeModalVisible = false;
    },
    updateMilestone(newMilestoneValue) {
      this.prize.milestone = newMilestoneValue;
    }
  },
  computed: {
    sortedPrizes(){
      let sortArray = this.prizes;
      return sortArray.sort((a,b) => a.milestone - b.milestone);
    }
  }
};
</script>

<style>
.prize-section {
 display: flex;
  flex-direction: column; /* Stack prize boxes vertically */
  align-items: center; /* Center horizontally */
  padding: 20px; /* Add some padding around prize boxes */
}
.prize-box {

  background-color: rgb(200, 240, 227);
  border: 3px solid #545454;
  border-radius: 10px;
  height: 200px;
  margin-top: 200px;
  margin-left: 20px;
  padding: auto;
  align-items: center;
  width: 100vw;
  
}
</style>
