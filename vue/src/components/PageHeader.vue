<template>
  <div >
    <h1>Hi <!--{{user.displayName}}--></h1>
  </div>
</template>

<script>
export default {
  name: 'page-header'

}
</script>

<style scoped>
  div {
    display: flex;
    flex-direction: column;
    background-color: bisque;
    justify-content: center;
    align-items: center;
    height: 100%;
  }
</style>