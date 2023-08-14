<template>
  <div id="rainbow-page">

    <avatar class="psychedelic-you"></avatar>
  </div>
</template>

<script>
import Avatar from '../components/Avatar.vue'

export default {
    name: "settings",
    components: {
        Avatar,
    }

}
</script>

<style scoped>

.rainbow-page {
    background-color: aqua;
}
body{
    background-color: aqua;
}
div{ background-color: aqua;
height: 100vh;
}

.psychedelic-you {
  animation: rainbow 3s linear infinite;
}

@keyframes rainbow {
  0% { filter: hue-rotate(0deg); }
  10% { filter: hue-rotate(30deg); }
  20% { filter: hue-rotate(60deg); }
  30% { filter: hue-rotate(90deg); }
  40% { filter: hue-rotate(120deg); }
  50% { filter: hue-rotate(150deg); }
  60% { filter: hue-rotate(180deg); }
  70% { filter: hue-rotate(210deg); }
  80% { filter: hue-rotate(289deg); }
  90% { filter: hue-rotate(300deg); }
  100% { filter: hue-rotate(360deg); }
}

  .psychedelic-you{padding:150px}
</style>