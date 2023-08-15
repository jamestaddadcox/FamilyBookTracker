<template>
  <div class="prize-box">
    <div class="left-column">
      <div class="top-row">
        <div class="edit-image">
          <img src="../assets/edit.png" alt="">
        </div>
        <h1 class="prize-name">{{ prize.name }}</h1>
      </div>
      <div class="middle-row">
        <div class="progress-wrapper">
        <progress-bar class="progress-bar" :progress="getProgressForPrize(prize)"></progress-bar>
      </div>
      </div>
      <div class="bottom-row">
        <p class="description"><b>Description:</b> {{ prize.description }}</p>
        <p class="start-date"><b>Start Date: </b>{{ prize.startDate }}</p>
        <p class="end-date"><b>End Date:</b> {{ prize.endDate }}</p>
   <div v-if="prize.milestone" class="milestone">
      <img src="../assets/check.png" alt="Checked" class="checked-icon">
      Completed
    </div>
      </div>
    </div>
        <div class="right-column">
      <div v-if="!prize.milestone" class="prize-image">
        <img src="../assets/greymedal1.png" alt="Grey Image">
      </div>
      <div v-else class="prize-image">
        <img src="../assets/ggg.png" alt="Prize Image">
      </div>
      </div>
  </div>
</template>

<script>
import ProgressBar from "../components/ProgressBar.vue";
import bookService from "../services/BookService"

export default {
  name: 'PrizeBox',
  props: ['prize'],
  data() {
    return {
      stats: []
    }
  },
  components: {
    ProgressBar
  },
  computed: {
      milestoneCheckboxState: {
        get() {
          return this.prize.milestone;
        },
        set(value) {
          this.$emit('toggle-milestone', value);
        }
      }
  },
  methods: {
    getProgressForPrize(prize) {  // prize is progress 
      prize
      return 50; 
    }
  },
  created() {
    if(!this.$store.state.user) return;

    bookService
      .getBookUsersByUserId(this.$store.state.user.userId)
      .then(response => {
        this.stats = response.data;
      });
  }
}
</script>

<style scoped>

.milestone {
  display: flex;
  align-items: center;
}

.checked-icon {
  width: 20px; /* Adjust the size as needed */
  height: 20px; /* Adjust the size as needed */
  margin-right: 5px;
}
.progress-bar{
  width: 50vw;
  height: 80px;
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
}

.prize-name {
  display: flex;
  font-size: 1.5rem;
  margin: 0;
  flex-grow: 3;
  justify-content: center;
  font-size: 80px;
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
  font-size: 25px;
  
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
  max-width: 140%; /* Ensure the image fits within the flex box */
  max-height: 100%; /* Ensure the image fits within the flex box */
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
