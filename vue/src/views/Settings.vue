<template>
  <div class="rainbow-page">
    <avatar class="psychedelic-you"></avatar>
  </div>
</template>

<script>
import Avatar from '../components/Avatar.vue';

export default {
  name: "avatar-container",
  components: {
    Avatar,
  },
};
</script>

<style scoped>
.rainbow-page {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  animation: rainbowBackground 3s linear infinite;
}

@keyframes rainbowBackground {
  0% { background: rgb(238, 130, 189); }
  14% { background: rgb(170, 50, 255); }
  28% { background: rgb(0, 195, 255); }
  42% { background: rgb(52, 255, 187); }
  57% { background: rgb(255, 255, 11); }
  71% { background: rgb(255, 182, 46); }
  85% { background: rgb(255, 56, 56); }
  100% { background: rgb(253, 0, 156); }
}

.psychedelic-you {
  animation: moveAvatar 4s ease-in-out infinite, rotateAvatar 6s linear infinite;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

@keyframes moveAvatar {
  0%, 100% {
    transform: translate(-50%, -50%);
  }
  25% {
    transform: translate(calc(-50% - 40px), calc(-50% - 40px));
  }
  50% {
    transform: translate(calc(-50% - 40px), calc(-50% + 40px));
  }
  75% {
    transform: translate(calc(-50% + 40px), calc(-50% + 40px));
  }
}

@keyframes rotateAvatar {
  0% { transform: translate(-50%, -50%) rotate(0deg); }
  100% { transform: translate(-50%, -50%) rotate(360deg); }
}
</style>

