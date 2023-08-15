<template>
<div>
  <button @click="openModal">Add Prize</button>
  
  <div class="bg-modal" v-show="isModalOpen">
      <div class="modal-content">
        <h2>Add Prize</h2>
        <div id="close" @click="closeModal">+</div>

        <div class="wrapper">
          <div class="radio-button">
            <div class="option">
              <input type="radio" name="count-option" id="number-of-books" v-model="selectedOption" value="books">
              <label for="number-of-books">Number of Books Finished</label> <br>
            </div>
            <div class="option">
              <input type="radio" name="count-option" id="number-of-pages" v-model="selectedOption" value="pages">
              <label for="number-of-pages">Number of Pages Read</label> <br>
            </div>
            <div class="option">
              <input type="radio" name="count-option" id="amount-of-time" v-model="selectedOption" value="time">
              <label for="amount-of-time">Amount of Time</label> <br>
            </div>
          </div>

          <div class="input-prize">
            <div class="popup-book" v-if="selectedOption === 'books'">
              <label for="book-dropdown">Select Number of Books:</label>
              <select name="dropdown" id="number-drop-down">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select>
            </div>

            <div class="popup-pages" v-if="selectedOption === 'pages'">
                <label for="number-of-pages">Number of Pages:</label>
              <input type="text" name="number-of-pages-prize" id="number-of-pages">
            </div>
            
            <div class="prize">
              <label for="prize">Prize:</label>
              <input type="text" name="prize" id="prize-input" required>
            </div>
            <div class="date-fields">
              <div class="start-date-prize">
                <label for="start-date">Start Date:</label>
                <input type="date" name="start-date" id="start-date" required>
              </div>
              <div class="end-date-prize">
                <label for="end-date">End Date:</label>
                <input type="date" name="end-date" id="end-date" required>
              </div>
            </div>
          </div>
        </div>

        <button type="submit">Go!</button>
            </div>
      </div>
  </div>
</template>

<script>
export default {
    data() {
		return {
			isModalOpen: false,
            selectedOption: ''

        }
    },

    methods: {

        openModal() {
			this.isModalOpen = true;
		},

        closeModal() {
            this.isModalOpen = false;
        },
    }
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
  z-index: 100;
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  width: 80%;
  max-width: 300px;
  background-color: white;
  color: #545454;
  padding: 20px;
  border-radius: 4px;
  text-align: left;
  position: relative;
}

.option-book {
    padding-top:10px;
}

.radio-button {
  margin-bottom: 10px;
}

.radio-button label {
  margin-bottom: 5px;
}

.popup-book {
  margin: auto; 
  text-align: center; 
}

.input-prize {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
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

#close {
  position: absolute;
  top: 0;
  right: 0;
  font-size: 35px;
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
</style>