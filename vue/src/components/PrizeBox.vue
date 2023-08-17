<template>
  <div class="prize-box">
    <div class="left-column">
      <div class="top-row">
        <div class="edit-image">
          <button class="edit-button" @click="openEditPrizeModal(prize.id)"><img src="../assets/edit.png" alt=""></button>
        </div>
        <h1 class="prize-name">{{ prize.name }}</h1>
      </div>
      <div class="middle-row">
        <div class="progress-wrapper">
          <progress-bar class="progress-bar" :maxValue="prize.goal" :progress="getProgressForPrize(prize)"></progress-bar>
        </div>
      </div>
      <div class="bottom-row">
        <p class="description"><b>Description:</b> {{ prize.description }}</p>
        <p class="start-date"><b>Start Date:</b> {{ prize.startDate }}</p>
        <p class="end-date"><b>End Date:</b> {{ prize.endDate }}</p>
        <div v-if="milestoneCheckboxState" class="milestone">
          <img src="../assets/check.png" alt="Checked" class="checked-icon">
          <b>Completed</b>
        </div>
      </div>
    </div>
    <div class="right-column">
      <div v-if="!milestoneCheckboxState" class="prize-image">
        <img src="../assets/greymedal1.png" alt="Grey Image">
      </div>
      <div v-else class="prize-image">
      <button class="prize-button" @click="claimPrize">
        <img src="../assets/ggg.png" alt="Prize Image">
      </button>
      <!-- CongratulationsModal component -->
        <CongratulationsModal :showModal="showCongratulationsModal" @close="closeCongratulationsModal" />
      </div>
    </div>
         <EditPrizeModal ref="editPrizeModalRef" :initialPrizeData="localPrize" :currentPrize="prize" />
  </div>
</template>

<script>
import CongratulationsModal from "../components/CongratulationsModal.vue";
import ProgressBar from "../components/ProgressBar.vue";
import bookService from "../services/BookService";
import EditPrizeModal from './EditPrizeModal.vue';

export default {
  name: 'PrizeBox',
  props: ['prize'],
  data() {
    return {
      stats: [],
      localPrize: { ...this.prize },
      showCongratulationsModal: false,
      confettiContainer: null
    }
  },
  components: {
    ProgressBar,
    CongratulationsModal,
    EditPrizeModal
  },
 
 computed: {
    milestoneCheckboxState() {
      return this.isProgressBarFull; // Return true if progress bar is full
    },
    isProgressBarFull() {
      return this.getProgressForPrize(this.prize) >= this.prize.goal;
    }
  },
  watch: {
    milestoneCheckboxState(newValue) {
      // Update prize.milestone when milestoneCheckboxState changes
      this.localPrize.milestone = newValue;
    }
  },
  methods: {
    openEditPrizeModal(prizeId) {
      if (this.$refs.editPrizeModalRef) {
        this.$refs.editPrizeModalRef.openModal(prizeId);
      } else {
        console.error("EditPrizeModal ref not found.");
      }
    },

    claimPrize() {
      this.showCongratulationsModal = true;
      this.showConfetti();
      this.$emit("claim-prize");
    },
    closeCongratulationsModal() {
      this.showCongratulationsModal = false;
    },
    showConfetti() {
        const explodeConfetti = () => {
        if (this.confettiContainer) {
          this.confettiContainer.innerHTML = ''; // Clear existing confetti
        } else {
          this.confettiContainer = document.createElement("div");
          // ... set confettiContainer styles ...
          document.body.appendChild(this.confettiContainer);
        }
  
  const confettiContainer = document.createElement("div");
  confettiContainer.style.position = "fixed";
  confettiContainer.style.top = 0;
  confettiContainer.style.left = 0;
  confettiContainer.style.width = "100%";
  confettiContainer.style.height = "100%";
  confettiContainer.style.pointerEvents = "none";
  confettiContainer.style.zIndex = 9999; // Ensure it's above other content
  document.body.appendChild(confettiContainer);

  const confettiColors = ["#f03355", "#ffa516", "#03a9f4", "#4caf50", "#ffeb3b"];
  const numConfetti = 300;
  for (let i = 0; i < numConfetti; i++) {
    const confetti = document.createElement("div");
    confetti.style.position = "absolute";
    confetti.style.width = "20px";
    confetti.style.height = "20px";
    confetti.style.backgroundColor = confettiColors[Math.floor(Math.random() * confettiColors.length)];
    confetti.style.borderRadius = "50%";
    confetti.style.transform = `translate(${Math.random() * 100}vw, ${Math.random() * 100}vh)`;
    confetti.style.transition = "transform 2s ease-out, opacity 2s ease-out";
    confetti.style.opacity = 0;
    confettiContainer.appendChild(confetti);

document.addEventListener("click", () => {
      for (let i = 0; i < numConfetti; i++) {
        const confetti = confettiContainer.children[i];
        if (confetti) {
          confetti.style.transform = `translate(${Math.random() * window.innerWidth}px, ${Math.random() * window.innerHeight}px)`;
        }
      }
    });

    setTimeout(() => {
      confetti.style.transform = `translate(${Math.random() * window.innerWidth}px, ${Math.random() * window.innerHeight}px)`;
      confetti.style.opacity = 1;
    }, 0);
  }

  setTimeout(() => {
    document.body.removeChild(confettiContainer);
    document.removeEventListener("click", explodeConfetti);
  }, 4000); // Adjust the duration as needed for the message to be displayed
}


     

      explodeConfetti(); // Initial explosion

      // Listen for page interactions to re-explode confetti
      document.addEventListener("click", explodeConfetti);

      // Stop the confetti explosion when the close button is clicked
      this.$once("close-modal", () => {
        document.removeEventListener("click", explodeConfetti);
        document.body.removeChild(this.confettiContainer);
      });
    },
    stopConfetti() {
      // Remove confetti container if exists and stop explosion
      if (this.confettiContainer) {
        document.removeEventListener("click", this.showConfetti);
        document.body.removeChild(this.confettiContainer);
        this.confettiContainer = null;
      }
    },   
    
    setMilestoneIfFull() {
      if (this.isProgressBarFull) {
        this.localPrize.milestone = true;
      }
    },

    getProgressForPrize(prize) {  // prize is progress 
    
    if (prize.goalType == 'time') {
    
      let totalminutes = this.stats.reduce((reducer,item) => {
        return reducer + item.minutesRead;
      },0);
      console.log(totalminutes);
      return totalminutes ; 
      }
    if (prize.goalType == 'pages') {

      let totalpages = this.stats.reduce((reducer, item) => {
        return reducer + item.pagesRead;
      }, 0);
      console.log(totalpages);
      return totalpages;
    }
    if (prize.goalType == 'books') {

      let totalbooks = this.stats.reduce((reducer, item) => {
        return reducer + item.completed;
      }, 0);
      console.log(totalbooks);
      return totalbooks;
    }  
    },
  },

  created() {
    if(!this.$store.state.user) return;

    bookService
      .getBookUsersByUserId(this.$store.state.user.userId)
      .then(response => {
        this.stats = response.data;
      });
      this.setMilestoneIfFull();
  }
}
</script>

<style scoped>

.edit-button {
  background: none;
  border: none;
}

.prize-button {
  background: none;
  border: none;
  cursor: pointer;
  padding: 0;
  box-shadow: yellow;
}

.milestone {
  display: flex;
  margin-top: 20px;
}

.checked-icon {
  width: 20px; /* Adjust the size as needed */
  height: 20px; /* Adjust the size as needed */
  margin-right: 5px;
}
.progress-bar{
  width: 100%;
  height: 50px;
}
.progress-wrapper {
  display: flex;
  justify-content: center;
  flex-grow: 1; /* Expand to take available space */
  margin: 0 10px; /* Add margin for spacing */
  align-items: center;
}
.prize-box {
  display: flex;
  background-color: white;
  border: 6px solid #545454;
  border-radius: 10px;
  width: 70vw;
  flex-grow: 1;
  margin-top: 20px;
  padding: 20px;
  font-family: "Dosis";
  color: #545454;
  
}

.left-column {
  flex: 2; /* Adjust the flex ratio as needed */
  display: flex;
  flex-direction: column;
  padding-right: 20px;
  align-self: stretch;
}

.top-row {
  display: flex;

  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
  flex-grow: 1;
}

.edit-image {
  display: flex;
  flex-grow: 0.2;
  justify-content: center;
  height: 70%;
  margin-bottom: 3px;
}

.prize-name {
  display: flex;
  margin: 0;
  flex-grow: 3;
  justify-content: center;
  font-size: 50px;
}

.middle-row {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 10px;
  flex-grow: 1;
}

.bottom-row {
  display: flex;
  flex-direction: row;
  justify-content: center;
  flex-grow: 1;
  align-self: stretch;
  font-size: 20px;
  text-align: left;
  
  
}

.right-column {
  flex: .50; /* Adjust the flex ratio as needed */
  display: flex;
  justify-content: center;
  align-items: center;
}

.prize-image {
  flex-grow: 0.15;
  display: flex;
  justify-content: center;
  align-items: center;
}

.prize-image img {
  max-width: 95%; /* Ensure the image fits within the flex box */
  max-height: 95% /* Ensure the image fits within the flex box */

}


.description {
  flex-grow: 2;
  text-align: center;
}

.start-date,
.end-date {
  flex-grow: 1;
  text-align: center;
}

.milestone {
  flex-grow: 0.5;
  display: flex;
  align-items: center;
  margin-bottom: 25px;
  justify-content: center;
  font-weight: 200px;
  
}


</style>