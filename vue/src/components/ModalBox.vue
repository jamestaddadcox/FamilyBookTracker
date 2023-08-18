<template>
    <transition name="modal-fade">
        <div class="modal-backdrop">
            <div class="modal">
                <header class="modal-header">
                    <slot name="header">
                        Welcome, New Book Worm!
                    </slot>
                    <button
                        type="button"
                        class="btn-close"
                        @click="close"
                    >
                        &times;
                    </button>
                </header>

                <section class="modal-body">
                    <slot name="body">
                        default body
                    </slot>
                </section>

                <footer class="modal-footer">
                    <slot name="footer">
                        default footer
                    </slot>
                    <button
                        type="button"
                        class="btn-green"
                        @click="close"
                    >
                        Close Modal
                    </button>
                </footer>
            </div>
        </div>
    </transition>

<!-- Add to connecting page:
<button
      type="button"
      class="btn"
      @click="showModal"
    >
      Open Modal!
    </button>
    
    <div>
    <modal-box
      v-show="isModalVisible"
      @close="closeModal"
    >
      <template v-slot:header>
      </template>

      <template v-slot:body>
      </template>

      <template v-slot:footer>
      </template>
    </modal-box>
    </div>
    -->

</template>

<script>
export default {
    name: 'ModalBox',
    methods: {
        close() {
            this.$emit('close');
        },
    },

/* data() {
      return {
        isModalVisible: false,
      };
    },
    methods: {
      showModal() {
        this.isModalVisible = true;
      },
      closeModal() {
        this.isModalVisible = false;
      }
    } */

};
</script>

<style>
    .modal-backdrop {
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background-color: rgba(0, 0, 0, 0.3);
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .modal {
        background-color: white;
        color: #545454;
        box-shadow: 2px 2px 20px 1px;
        overflow-x: auto;
        display: flex;
        flex-direction: column;
        border-radius: 5px;
    }

    .modal-header,
    .modal-footer {
        padding: 15px;
        display: flex;
    }

    .modal-header {
        position: relative;
        border-bottom: 1px solid white;
        color: blue;
        justify-content: space-between;
        
    }

    .modal-footer {
        border-top: 1px solid white;
        flex-direction: column;
        justify-content: flex-end;
    }

    .modal-body {
        position: relative;
        padding: 20px 10px;
    }

    .btn-close {
        position: absolute;
        top: 0;
        right: 0;
        border: none;
        font-size: 20px;
        padding: 10px;
        cursor: pointer;
        font-weight: bold;
        color: cyan;
        background: transparent;
    }

    .btn-green {
        color: white;
        background: cyan;
        border: 1px solid #4AAE9B;
        border-radius: 5px;
        padding: 5px;
    }

    .modal-fade-enter,
    .modal-fade-leave-to {
        opacity: 0;
    }

    .modal-fade-enter-active,
    .modal-fade-leave-active {
        transition: opacity .5s ease;
    }
</style>