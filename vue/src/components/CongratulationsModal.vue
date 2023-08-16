<template>
  <div class="modal-overlay" v-if="showModal">
    <div class="modal-content">
      <div class="close-button" @click="closeModal">x</div>
      <img class="congrats" src="../assets/congrats.png">
      <h1 class="congratulations-title">Congratulations!</h1>
      <p class="congratulations-text">You've earned your prize!</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    showModal: Boolean
  },
  methods: {
    closeModal() {
      this.$emit('close');
      this.isModalOpen = false;
      this.$emit('close-modal');
    }
  }
};
</script>


<style scoped>
h1 {
  font-family: "Dosis";
  font-size: 90px;
  margin: 0;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
}

.modal-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  position: relative;
  width: 80%;
  max-width: 800px;
  height: auto;
  max-height: 80%;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 50px;
  font-family: "Dosis";
  margin-right: 20px;
  cursor: pointer;
  z-index: 1000;
  color: red;
}

.congrats {
  flex-grow: 2;
  margin-top: 10px;
  max-width: 60%; /* Increase the image width */
  max-height: 60%; /* Increase the image height */
  margin-bottom: 10px; /* Add space below the image */
}

p {
  font-family: "Dosis";
  font-size: 40px;
  margin: 0;
  flex-grow: 1;
  padding-bottom: 2%; /* Decrease the padding */
}
</style>