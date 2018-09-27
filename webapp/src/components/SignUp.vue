<template>
  <div class="h-login-form">
    <h2 class="h-login-head">Create your Account</h2>
    <form @submit.prevent="signUp" novalidate>
      <div class="form-group">
        <input v-validate="'required'" name="firstName" type="text" class="form-control" placeholder="First Name" v-model="firstName">
        <span class="h-validate-error">{{errors.first('firstName')}}</span>
      </div>
      <div class="form-group">
        <input v-validate="'required'" name="lastName" type="text" class="form-control" placeholder="Last Name" v-model="lastName">
        <span class="h-validate-error">{{errors.first('lastName')}}</span>
      </div>
      <div class="form-group">
        <input v-validate="'required|email'" name="email" type="email" class="form-control" placeholder="Email" v-model="email">
        <span class="h-validate-error">{{errors.first('email')}}</span>
      </div>
      <div class="form-group">
        <input v-validate="'required|min:6'" name="password" type="password" class="form-control" placeholder="Password" v-model="password">
        <span class="h-validate-error">{{errors.first('password')}}</span>
      </div>
      <div class="form-group">
        <input v-validate="'required|min:6'" name="repassword" type="password" class="form-control" placeholder="Confirm Password" v-model="repassword">
        <span class="h-validate-error">{{errors.first('repassword')}}</span>
      </div>
      <button type="submit" class="btn btn-primary h-login-btn">Continue</button>
    </form>
  </div>
</template>

<script>
  import API from '../constants/apiUrl.js';
  export default {
    data() {
      return {
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        repassword: ''
      }
    },
    methods: {
      signUp() {
        const apiUrl = API.SERVER_BASE_URL + API.post_signup;
        const paramData = {
          email: this.email,
          password: this.password,
          firstName: this.firstName,
          lastName: this.lastName
        };
        this.$http.post(apiUrl, paramData).then(response => {
          console.log(response);
        }, errResponse => {
          console.error(errResponse);
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
    .h-login-form {
    width: 340px;
    background-color: #f7f7f7;
    padding: 40px 40px 60px 40px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  }
  .h-login-btn {
    width: 100%;
  }
  .h-login-head {
    text-align: center;
    font-size: 24px;
    font-weight: 400;
    margin-bottom: 22px;
  }
</style>
