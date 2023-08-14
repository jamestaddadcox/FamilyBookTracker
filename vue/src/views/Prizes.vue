<template>
  <page-layout>
    <template v-slot:headerbar>
      <page-header></page-header>
    </template>

    <template v-slot:content>
        <div>hi</div>
    </template>

    <template v-slot:sidebar>
      <side-bar-menu></side-bar-menu>
    </template>

  </page-layout>
</template>

<script>

import PageLayout from '../views/PageLayout.vue';
import SideBarMenu from '../components/SideBarMenu.vue';
import PageHeader from '../components/PageHeader.vue';


export default {
  name: "prizes-view",
  components: {
    PageLayout,
    SideBarMenu,
    PageHeader,
  },
};
</script>
<style scoped>

</style>