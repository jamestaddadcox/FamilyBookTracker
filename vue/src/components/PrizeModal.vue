<template>
  <div>
    <button class="add-prize" @click="openModal">Add Prize</button>

    <div class="bg-modal" v-show="isModalOpen">
      <form @submit.prevent="addPrize">
        <div class="modal-content">
          <h2>Add Prize</h2>
          <div id="close" @click="closeModal">+</div>
          
        <div class="wrapper">
          <div class="radio-button">
            <div class="option">
              <input type="radio" name="count-option" id="number-of-books" v-model="newPrize.goalType" value="books">
              <label for="number-of-books">Number of Books Finished</label> <br>
            </div>
            <div class="option">
              <input type="radio" name="count-option" id="number-of-pages" v-model="newPrize.goalType" value="pages">
              <label for="number-of-pages">Number of Pages Read</label> <br>
            </div>
            <div class="option">
              <input type="radio" name="count-option" id="amount-of-time" v-model="newPrize.goalType" value="time">
              <label for="amount-of-time">Amount of Time</label> <br>
            </div>
          </div>
        </div>

          <div class="input-prize">
            <div class="popup-book" v-if="newPrize.goalType === 'books'">
              <label for="book-dropdown">Select Number of Books:</label>
              <select name="dropdown" id="book-dropdown" v-model="newPrize.goal">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select>
            </div>

            <div class="popup-pages" v-if="newPrize.goalType === 'pages'">
                <label for="number-of-pages-prize">Number of Pages:</label>
                <input type="text" name="number-of-pages-prize" id="number-of-pages" maxlength="5" integer v-model="newPrize.goal">
            </div>

           <div class="popup-time" v-if="newPrize.goalType === 'time'">
                <label for="datetime">Time:</label> <br>
                <input type="text" name="datetime" id="amount-of-time" placeholder="Minutes" integer v-model="newPrize.goal">
            </div>
            <div class="prize">
              <label for="prize">Prize:</label>
              <input type="text" name="prize" id="prize-input" required v-model="newPrize.name">
            </div>

            <div class="description-prize">
                <label for="description">Description:</label>
                <textarea name="description-text-area" id="prize-description" maxlength="200" v-model="newPrize.description"></textarea>
            </div>

            <div class="checkboxes">
            <div class="adults">
                <label for="adult-user">Adult:</label>
                <input type="radio" name="user-group" id="adult-user" value="adult" v-model="selectedUserGroup">
            </div>

            <div class="children">
                <label for="child-user">Child:</label>
                <input type="radio" name="user-group" id="child-user" value="child" v-model="selectedUserGroup">
            </div>

            <div class="both">
                <label for="both-user">Both:</label>
                <input type="radio" name="user-group" id="both-user" value="both" v-model="selectedUserGroup">
            </div>
            </div>

            <div class="date-fields">
            <div class="start-date-prize">
              <label for="start-date">Start Date:</label>
              <input type="date" name="start-date" id="start-date" required v-model="newPrize.startDate">
            </div>
            <div class="end-date-prize">
              <label for="end-date">End Date:</label>
              <input type="date" name="end-date" id="end-date" required v-model="newPrize.endDate">
            </div>
          </div>

          <button type="submit">Go!</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import PrizeService from '../services/PrizeService.js';

export default {
  name: "PrizeModal",
  props:['prize'],
  data() {
    return {
      isModalOpen: false,
      selectedOption: '',
      selectedUserGroup: '',
      newPrize: {
        "name": '',
        "description": '',
        "milestone": false,
        "startDate": '',
        "userGroup": '',
        "endDate": '',
        "goal": '',
        "goalType": ''
        
      }
    };
  },
  watch: {
    selectedUserGroup: 'updateUserGroup',
    selectedOption: 'updatedSelectedOption'
  },
    methods: {
        openModal() {
            this.isModalOpen = true;
        },

        closeModal() {
            this.isModalOpen = false;
            this.resetModal();
        },

        resetModal() {
            this.selectedOption = '';
            this.newPrize.prize_name = '';
            this.newPrize.prize_description = '';
            this.newPrize.milestone = false;
            this.newPrize.start_date = '';
            this.newPrize.user_group = '';
            this.newPrize.end_date = '';
            this.newPrize.goal = '';
        },

        updateUserGroup() {
           this.newPrize.userGroup = this.selectedUserGroup;
        },

        updateSelectedOption() {
          this.newPrize.goalType = this.selectedOption;
        },

        addPrize() {
          this.newPrize.familyId = this.$store.state.user.familyId;
            PrizeService.addPrize(this.newPrize)
            .then((response) => {
              response.data;
            this.resetModal();
            this.closeModal();

            })
      }   
    },
  } 
</script>

<style scoped>
.bg-modal {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  width: 80%;
  max-width: 500px;
  background-color: white;
  padding: 20px;
  border-radius: 4px;
  text-align: left;
  position: fixed; /* Change position to fixed */
  top: 50%; /* Center vertically */
  left: 50%; /* Center horizontally */
  transform: translate(-50%, -50%); /* Adjust to center perfectly */
}

h2 {
    margin-top: 0px;
}

.option-book {
    padding-top:10px;
}

.radio-button {
  margin-bottom: 7px;
  margin-top: 7px;
}

.radio-button label {
  margin-bottom: 5px;
}

.checkboxes {
    display: flex;
    justify-content: space-between;
    margin-bottom: 15px;
    margin-top: 10px;
}

#adult-user,
#child-user,
#both-user 
{
    margin-right: 40px;
}



.popup-pages, 
.popup-time, 
.popup-book {
    margin-bottom: 7px;
}

.popup-time input {
    width: 218px;
    margin-left: 2px;
}

.popup-pages input {
    width: 95px;
    margin-left: 2px;
}

.input-prize {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.description-prize {
    display: flex;
    flex-direction: column;
    margin-bottom: 10px;
}

#prize-description {
    width: 270px;
    height: 50px;
}

.date-fields {
    display: flex;
    gap: 20px;
}

#end-date,
#start-date,
#prize-input {
  width: 100%; 
  margin-bottom: 10px;
}

.wrapper > button {
  margin-top: 20px;
  width: 50px;
}

.input-prize {
    padding-bottom: 10px;
}

#close {
  position: absolute;
  top: 0;
  right: 14px;
  font-size: 38px;
  transform: rotate(45deg);
  cursor: pointer;
}
.popup-book {
  margin-top: 10px;
  display: flex;
  align-items: center;
}
.popup-book label {
  margin-right: 10px;
}
.popup-book select {
  width: auto;
}

.add-prize {

  background-color: #2391f8;
  border: 3px solid #2391f8;
  border-radius: 10px;
  height: 90px;
  padding: auto;
  align-items: center;
  width: 12vw;
  font-family: "Dosis";
  color: white;
  font-size: 50px;
}
</style>




