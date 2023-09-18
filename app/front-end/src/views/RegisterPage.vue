<template>
  <div class="container public">
    <div class="row justify-content-center">
      <div class="form">
        <!-- 상단 로그인 이미지, 간단 소개-->
        <Logo />
        <!-- 회원가입 Form 요소 -->
        <form @submit.prevent="submitForm">
          <!-- 에러 메시지 출력-->
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <!-- Form Group-->
          <div class="form-group">
            <!-- 사용자 이름 입력-->
            <label for="username">{{ $t('registerPage.form.username.label') }}</label>
            <input type="text" class="form-control" id="username" v-model="form.username" />
            <!-- 사용자 이름 입력 오류 메시지 -->
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div class="error" v-if="!$v.form.username.required">{{ $t('registerPage.form.username.required') }}</div>
              <div class="error" v-if="!$v.form.username.alphaNum">{{ $t('registerPage.form.username.alphaNum') }}</div>
              <div class="error" v-if="!$v.form.username.minLength">{{ $t('registerPage.form.username.minLength', {minLength: $v.form.username.$params.minLength.min}) }}</div>
              <div class="error" v-if="!$v.form.username.maxLength">{{ $t('registerPage.form.username.maxLength', {maxLength: $v.form.username.$params.maxLength.max}) }}</div>
            </div>
          </div>

          <div class="form-group">
            <!-- 사용자 이메일 주소 입력 -->
            <label for="emailAddress">{{ $t('registerPage.form.emailAddress.label') }}</label>
            <input type="email" class="form-control" id="emailAddress" v-model="form.emailAddress">
            <!-- 사용자 이메일 입력 오류 메시지 -->
            <div class="field-error" v-if="$v.form.emailAddress.$dirty">
                <div class="error" v-if="!$v.form.emailAddress.required">{{ $t('registerPage.form.emailAddress.required') }}</div>
                <div class="error" v-if="!$v.form.emailAddress.email">{{ $t('registerPage.form.emailAddress.email') }}</div>
                <div class="error" v-if="!$v.form.emailAddress.maxLength">{{ $t('registerPage.form.emailAddress.maxLength', {maxLength: $v.form.emailAddress.$params.maxLength.max}) }}</div>
              </div>
          </div>

          <div class="form-group">
            <!-- 사용자 비밀번호 입력 -->
            <label for="password">{{ $t('registerPage.form.password.label') }}</label>
            <input type="password" class="form-control" id-="password" v-model="form.password">
            <!-- 사용자 비밀번호 입력 오류 메시지 -->
            <div class="field-error" v-if="$v.form.password.$dirty">
                <div class="error" v-if="!$v.form.password.required">{{ $t('registerPage.form.password.required') }}</div>
                <div class="error" v-if="!$v.form.password.minLength">{{ $t('registerPage.form.password.minLength', {minLength: $v.form.password.$params.minLength.min}) }}</div>
                <div class="error" v-if="!$v.form.password.maxLength">{{ $t('registerPage.form.password.maxLength', {maxLength: $v.form.password.$params.maxLength.max}) }}</div>
              </div>
          </div>

          <!-- 가입하기 버튼 -->
          <button type="submit" class="btn btn-primary btn-block">{{ $t('registerPage.form.submit') }}</button>
          <p class="accept-terms text-muted">
            <i18n path="registerPage.form.terms.accept" tag="p" class="aceept-terms text-muted">
              <a href="#" place="termsOfService">{{ $t('registerPage.form.terms.termsOfService') }}</a>
              <a href="#" place="privacyPolicy">{{ $t('registerPage.form.terms.privacyPolicy') }}</a>
            </i18n>
          </p>
          <p class="text-center text-muted">{{ $t('registerPage.form.alreadyHaveAccount')}} <router-link to="/login">{{ $t('registerPage.form.signIn') }}</router-link></p>
        </form>
      </div>
    </div>
   <PageFooter />
  </div>
</template>

<script>
import { required, email, minLength, maxLength, alphaNum } from 'vuelidate/lib/validators'
import registrationService from '@/services/registration'
import Logo from '@/components/Logo.vue'
import PageFooter from '@/components/PageFooter.vue'

export default {
  name: 'RegisterPage',
  components: { PageFooter, Logo },
  data: function () {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  validations: {
    form: {
      username: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(50),
        alphaNum
      },
      emailAddress: {
        required,
        email,
        maxLength: maxLength(100)
      },
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30)
      }
    }
  },
  methods: {
    /**
     * 폼 데이터 검증하기
     */
    submitForm () {
      this.$v.$touch()

      if (this.$v.$invalid) {
        return
      }

      registrationService.register(this.form).then(() => {
        this.$router.push({ name: 'LoginPage' })
      }).catch((error) => {
        this.errorMessage = 'Failed to register user. ' + error.message
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.accept-terms {
    margin: 20px 0 40px 0;
 }
</style>
