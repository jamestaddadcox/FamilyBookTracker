<template>
  <transition name="modal-fade">
    <div class="modal-backdrop">
      <div class="modal">
        <header class="modal-header">
          Change Your Avatar
          <button type="button" class="btn-close" @click="close">&times;</button>
        </header>

        <section class="modal-body">
            <file-upload></file-upload>
        </section>


      </div>
    </div>
  </transition>
</template>

<script>
import FileUpload from './FileUpload.vue';

export default {
    components: {
        FileUpload
    },
    methods: {
    close() {
      this.clearForm();
      this.$emit("close");
    },
  },
};
</script>

<style>
</style>