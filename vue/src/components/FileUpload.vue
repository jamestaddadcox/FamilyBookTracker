<template>
  <div id="avatar-upload">
    <button class="route-user" id="avatar-home" @click="goHome"><avatar title="Go Home"></avatar></button>
    <button><img src="" alt=""></button>
    <input
      type="file"
      @change="handleFileChange"
      ref="fileInput"
      accept="image/*"
    />
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
    goHome(){
      this.$router.push('/');
    },
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
#avatar-upload{
  display: flex;
  flex-direction: column;
  align-items: center;
}
#avatar-home{
  display: flex;
  width: 125px;
  height: 125px;
  border-radius: 50%;
  align-items: center;
  justify-content: center;
  box-shadow: 0px 0px 19px bisque;
}
</style>
