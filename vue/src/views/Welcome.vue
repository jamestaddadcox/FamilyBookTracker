<template>
  <div class="welcome">
    <img class="logo" src="../assets/LargeWormWagon.png" alt="worm wagon">
    <h2>A Family Book Logging App</h2>
    <div id="button-box">
    <button title="Click here to Log into your account" @click="goToLogIn">Login</button>
    <button title="Parents can register here" @click="goToRegister">Register</button>
    </div>
    <img id="worm-on-book" class="worm-pic" src='@/assets/knockout2.png' alt="worm">
    <img id="book-pic" src="@/assets/books.png" alt="books">
    <img  />
    
    <a href="#">FAQ</a>
  </div>
</template>

<script>
export default {
    data(){
        return {
        }
    },
    computed:{
        
    },
    methods: {
        goToLogIn(){
            this.$router.push('/login');
        },
        goToRegister(){
            this.$router.push('/register');
        }
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
    #worm-on-book {
        width: 200px;
        flex-shrink: 1;
        z-index: 1;
        margin-bottom: -30px;
        
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
    </style>