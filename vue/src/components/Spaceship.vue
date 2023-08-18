<template>
  <div class="spaceship">
    <img src="../assets/spaceship.png" alt="Spaceship" />
  </div>
</template>

<script>
export default {
  name: "Spaceship",
};
</script>

<style scoped>
.spaceship {
  position: absolute;
  transition: transform 0.5s ease-in-out;
}
</style>