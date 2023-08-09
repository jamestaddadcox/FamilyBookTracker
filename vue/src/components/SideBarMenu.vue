<template>
  <div class="sidebar">
    <!-- <img class="avatar" src="@/assets/TestAvatar.png" alt="test"> -->
    <menu class="link-list">
        <li class="active-left-menu">
          <a href="#">Family</a>
        </li>
        <li>
          <a href="#">Stats</a>
        </li>
        <li>
          <a href="#">Add-Progress</a>
        </li>
        <li>
          <a href="#">Home</a>
        </li>
        <div class="logout-settings">
          <a href="#">logout</a>
          <a href="#">settings</a>
        </div>
    </menu>
  </div>
</template>

<script>
export default {
  name: "side-bar-menu"

}
</script>

<style>
div .side-bar{
  background-color: coral;
}
</style>