<template>
   <div class="container h-signup-container">
      <sign-up-dialog></sign-up-dialog>
  </div>
</template>

<script>
  import SignUpDialog from '../components/SignUp.vue';
  export default {
    components: {
      SignUpDialog
    }
  }
</script>

<style lang="scss" scoped>
  .h-signup-container {
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

</style>
