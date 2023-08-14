<template>
  <div class="welcome">
    <div class="header">
      <h1 class="app-title">WORM WAGON</h1>
      <h2>A FAMILY BOOK LOGGING APP</h2>
    </div>
    
    <img src="@/assets/simple.gif" alt="Image" class="centered-image" />

    <div class="button-container">
      <button class="btn left-btn" @click="showLoginModal">SIGN IN</button>
      <button class="btn right-btn" @click="showRegisterModal">SIGN UP</button>
    </div>

    <div class="modal">
    <login-modal v-show="isLoginModalVisible" @close="closeLoginModal"></login-modal>
    <registration-modal v-show="isRegistrationModalVisible" @close="closeRegistrationModal"></registration-modal>
    </div>

    <footer class="footer">
      <div class="logo-container">
          <h3>GATE TECHNOLOGY</h3>
        
      </div>
      <div class="footer-links">
        <router-link to="/about">About</router-link>

        <router-link to="/careers">Careers</router-link>

        <router-link to="/contact">Contact Us</router-link>
      </div>
    </footer>
  </div>
</template>

<script>
import LoginModal from '../components/LoginModal.vue';
import RegistrationModal from '../components/RegistrationModal.vue';

export default {
  components: {
    LoginModal,
    RegistrationModal
  },
  data() {
    return {
      isLoginModalVisible: false,
      isRegistrationModalVisible: false
    };
  },
  methods: {
    showLoginModal() {
      this.isLoginModalVisible = true;
    },
    showRegisterModal() {
      this.isRegistrationModalVisible = true;
    },
    closeLoginModal() {
      this.isLoginModalVisible = false;
    },
    closeRegistrationModal() {
      this.isRegistrationModalVisible = false;
    }
  },
  name: "WelcomePage"
};
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Belanosima:wght@700&family=Berkshire+Swash&family=Bungee&family=Cabin+Sketch&family=Calistoga&family=Caprasimo&family=Cherry+Bomb+One&family=Crete+Round&family=Dosis:wght@600;700&family=Fjalla+One&family=Fredericka+the+Great&family=Libre+Baskerville&family=Limelight&family=Monoton&family=Noto+Serif+Makasar&family=Playfair+Display:wght@500&family=Quicksand:wght@400;500;600;700&family=Rye&family=Sacramento&family=Shrikhand&family=Source+Sans+3:wght@300&family=Special+Elite&family=Staatliches&family=Ultra&family=Varela&display=swap');

.welcome {
  background-color: #f3e3df;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  height: 100vh;
  padding-top: 1vh;
}

.header {
  margin-top: 10px;
}

.app-title {
  font-family: "Limelight", cursive;
  color: #545454;
  font-size: 10vw;
  text-align: center;
  margin-bottom: 5px;
  margin-top: 10px;
}

h2 {
  font-size: 2vw;
  font-family: "Varela";
  text-align: center;
  color: #545454; 
  margin-bottom: 2px;
  margin-top: -20px; 
}

h3 {
    font-family: 'Lucida Sans';
}

.btn {
  padding: 15px 30px;
  font-family: "Dosis";
  font-size: 25px;
  color: #545454;
  border: 4px solid #545454;
  border-radius: 30px;
  cursor: pointer;
  margin: 7px;
  background-color: #f3e3df;
  transition: background-color 0.3s ease, color 0.3s ease, border-color 0.3s ease;
}

.button-container {
   display: flex;
  justify-content: space-between;
  position: absolute;
  bottom: 20px;
  left: 20px;
  right: 20px;
}
left-btn {
  background-color: #f3e3df;
  color: #545454;
  border-color: #545454;
  border-radius: 30px 0 0 30px;
}

right-btn {
  background-color: #f3e3df;
  color: #545454;
  border-color: #545454;
  border-radius: 0 30px 30px 0;
}

.btn:hover {
  background-color: #545454;
  color: #f3e3df;
  border-color: #f3e3df;
}

#button-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.button {
  margin: 7px;
}
    .alertmsg {
        background-color: red;
    }

        .modal {
        font-family: "Dosis", san-serif;
        background-color: #FFFFFF;
        box-shadow: 2px 2px 20px 1px;
        overflow-x: auto;
        display: flex;
        flex-direction: column;
        border-radius: 5px;
        z-index: 5;
        
    }

.modal-backdrop {
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background-color: rgba(0, 0, 0, 0.3);
        display: flex;
        justify-content: center;
        align-items: center;
    }

.centered-image {
  display: block;
  margin: 20px auto;
  max-width: 60vw;
}

.footer {
  background-color: #f3e3df;
  color: #545454;
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo-image {
    max-width: 10vw;
}


</style>
