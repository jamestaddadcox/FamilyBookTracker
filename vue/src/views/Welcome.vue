<template>
  <div class="welcome">
    <img class="logo" src="../assets/LargeWormWagon.png" alt="worm wagon">
    <button @click="goToLogIn">Login</button>
    <button @click="goToRegister">Register</button>
    <img class="worm-pic" src='@/assets/knockout2.png' alt="worm">
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
    }
    img.logo {
        width: 75%;
        padding: 75px;
        height: auto;
        
        
    }
    .worm-pic{
        width: 200px;
        flex-shrink: 1;
    }

    img.logo:hover {
    animation: rainbow 7s linear infinite;
    }

    @keyframes rainbow {
    0% { filter: hue-rotate(0deg); }
    14% { filter: hue-rotate(60deg);  }
    28% { filter: hue-rotate(120deg); }
    42% { filter: hue-rotate(180deg); }
    57% { filter: hue-rotate(240deg); }
    71% { filter: hue-rotate(300deg); }
    85% { filter: hue-rotate(360deg); }
    100% { filter: hue-rotate(0deg); }
}
    </style>