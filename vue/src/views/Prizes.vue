<template>
  <page-layout>
    <template v-slot:headerbar>
      <page-header></page-header>
    </template>

    <template v-slot:content>
        <prize-modal></prize-modal>
        <edit-prize-modal></edit-prize-modal>
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
import PrizeModal from '../components/PrizeModal.vue';
import EditPrizeModal from '../components/EditPrizeModal.vue';

export default {
  name: "prizes-view",
  components: {
    PageLayout,
    SideBarMenu,
    PageHeader,
    PrizeModal,
    EditPrizeModal
  },
};
</script>
<style scoped>

</style>