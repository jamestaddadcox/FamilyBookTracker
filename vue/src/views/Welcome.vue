<template>
  <div class="welcome">
    <img class="logo" src="../assets/LargeWormWagon.png" alt="worm wagon">

        <h2>A Family Book Logging App</h2>
        <div id="button-box">
        <button @click="showLoginModal">sign in</button>
        <button @click="showRegisterModal">sign up</button>  
        </div>
    
<login-modal
      v-show="isLoginModalVisible"
      @close="closeLoginModal"
    >
</login-modal> 
<registration-modal
      v-show="isRegistrationModalVisible"
      @close="closeRegistrationModal">
</registration-modal>
<add-book-modal
      v-show="isAddBookModalVisible"
      @close="closeAddBookModal"
      > 
    </add-book-modal>

    <img id="worm-on-book" class="worm-pic" src='@/assets/knockout2.png' alt="worm">
    <img id="book-pic" src="@/assets/books.png" alt="books">

    
    <a href="#">FAQ</a>
  </div>
</template>

<script>
import AddBookModal from '../components/AddBookModal.vue';
import LoginModal from '../components/LoginModal.vue';
import RegistrationModal from '../components/RegistrationModal.vue';

export default {
    components: {
        LoginModal,
        RegistrationModal,
        AddBookModal
    },
    data() {
      return {
        isLoginModalVisible: false,
        isRegistrationModalVisible: false,
        isAddBookModalVisible: false,
        };
    },
    computed:{
        
    },
    methods: {
        showLoginModal(){
            this.isLoginModalVisible = true;
        },
        showRegisterModal(){
            this.isRegistrationModalVisible = true;
        },
        closeLoginModal() {
            this.isLoginModalVisible = false;
        },
        closeRegistrationModal() {
            this.isRegistrationModalVisible = false;
        },
        showAddBookModal() {
            this.isAddBookModalVisible = true;
        },
        closeAddBookModal() {
            this.isAddBookModalVisible = false;
        }

        // goToLogIn(){
        //     this.$router.push('/login');
        // },
        // goToRegister(){
        //     this.$router.push('/register');
        // }
    },
    
    name: "welcome"

};
</script>



<style>
    div.welcome{
        display: flex;
        flex-direction: column;
        align-items: center;
        position: relative; 
    }
    img.logo {
        width: 75%;
        padding: 75px;
        height: auto;
        padding-bottom: 30px;
        
        
    }
    #button-box{
        display: flex;
        flex-direction: column;
        margin: 20px;
    }
    button {
        margin: 7px;
    }

    /* #book-pic {
        z-index: -1;
    } */

    #worm-on-book {
        width: 200px;
        flex-shrink: 1;
        z-index: auto; 
        margin-bottom: -30px;
        z-index: 1;
    }

    img.logo:hover {
    animation: rainbow 7s linear infinite;
    }

    img.book-pic {
        margin-top: -15%;
    }

    @keyframes rainbow {
    0% { filter: hue-rotate(0deg); }
    14% { filter: hue-rotate(60deg);  }
    28% { filter: hue-rotate(120deg); }
    42% { filter: hue-rotate(180deg); }
    57% { filter: hue-rotate(240deg); }
    71% { filter: hue-rotate(300deg); }
    85% { filter: hue-rotate(360deg); }
    100% { filter: hue-rotate(390deg); }
}

/* modal styling */

    .alertmsg {
        background-color: red;
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
        z-index: 60;
    }

    .modal {
        background: #FFFFFF;
        box-shadow: 2px 2px 20px 1px;
        overflow-x: auto;
        display: flex;
        flex-direction: column;
        border-radius: 5px;
        z-index: 70;
    }

    .modal-header,
    .modal-footer {
        padding: 15px;
        display: flex;
    }

    .modal-header {
        position: relative;
        border-bottom: 1px solid #eeeeee;
        color: blue;
        justify-content: space-between;
    }

    .modal-footer {
        border-top: 1px solid #eeeeee;
        flex-direction: column;
        justify-content: flex-end;
    }

    .modal-body {
        position: relative;
        padding: 20px 10px;
    }

    .btn-close {
        position: absolute;
        top: 0;
        right: 0;
        border: none;
        font-size: 20px;
        padding: 10px;
        cursor: pointer;
        font-weight: bold;
        color: cyan;
        background: transparent;
    }

    .btn-green {
        color: white;
        background: cyan;
        border: 1px solid #4AAE9B;
        border-radius: 5px;
        padding: 5px;
    }

    .modal-fade-enter,
    .modal-fade-leave-to {
        opacity: 0;
    }

    .modal-fade-enter-active,
    .modal-fade-leave-active {
        transition: opacity .5s ease;
    }

    /*end of modal styling section */
    </style>