<template>
  <div class="progress-3" :style="progressBarStyle"></div>
</template>

<script>
export default {
  props: {
      progress: { // Current progress (adjust as needed)
        type: Number,
        default: 0
      },
      maxValue: {
          type: Number,
          default: 0
      }
  },
  data() {
    return {
      maxSection: 100,  // Max value for the 100% section
    };
  },
  computed: {
    progressBarStyle() {
      const backgroundSize = `${(this.progress / this.maxValue) * 100}% 100%`;

      return {
        background: `
          repeating-linear-gradient(135deg, #fbe639 0 10px, #ffa516 0 20px) 0/0% no-repeat,
          repeating-linear-gradient(135deg, #ddd 0 10px, #eee 0 20px) 0/100%`,
          'background-size': backgroundSize,
          'border-radius': '40px'
      };
    }
  }
};
</script>

<style>
/* Your existing styles here */

@keyframes p3 {
  100% {
    background-size: 100%;
  }
}
</style>
