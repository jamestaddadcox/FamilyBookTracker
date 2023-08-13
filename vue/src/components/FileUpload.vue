<template>
  <div>
    <input
      style="display: none"
      type="file"
      @change="handleFileChange"
      ref="fileInput"
      accept="image/*"
    />
    <span @click="openFileInput">
      <avatar></avatar>
    </span>
    <button @click="uploadFile" title="Upload">Upload</button>
  </div>
</template>

<script>
import axios from 'axios';
import Avatar from '@/components/Avatar.vue';

export default {
  components: {
    Avatar
  },
  name: 'file-uploader',
  data() {
    return {
      selectedFile: null
    };
  },
  methods: {
    openFileInput() {
      this.$refs.fileInput.click();
    },
    handleFileChange(event) {
      const file = event.target.files[0];
      if (file && this.isFileSizeValid(file.size)) {
        this.selectedFile = file;
      }
    },
    isFileSizeValid(fileSize) {
      const maxSizeInBytes = this.maxSize * 1024 * 1024; // Converts maxSize to bytes
      return fileSize <= maxSizeInBytes;
    },
    uploadFile(id) {
      if (!this.selectedFile) {
        return;
      }
      const fd = new FormData();
      fd.append('image', this.selectedFile, this.selectedFile.name);
      axios
        .put(`/user/${id}/avatar`, fd, {
          onUploadProgress: uploadEvent => {
            console.log('Upload Progress: ' + Math.round((uploadEvent.loaded / uploadEvent.total) * 100) + '%');
          }
        })
        .then(res => {
          console.log(res);
        });
    }
  },
  props: {
    maxSize: {
      type: Number,
      default: 5
    }
  }
};
</script>

<style>
</style>
