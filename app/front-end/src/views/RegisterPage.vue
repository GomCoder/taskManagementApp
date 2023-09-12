<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <!-- 상단 로그인 이미지, 간단 소개-->
        <div class="logo-wrapper">
          <img src="@/images/logo.png" alt="로고 이미지" class="logo">
          <div class="tagline">Open source task management tool</div>
        </div>
        <!-- 회원가입 Form 요소 -->
        <form @submit.prevent="submitForm">
          <!-- 에러 메시지 출력-->
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <!-- Form Group-->
          <div class="form-group">
            <!-- 사용자 이름 입력-->
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" v-model="form.username" />
            <!-- 사용자 이름 입력 오류 메시지 -->
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div class="error" v-if="!$v.form.username.required">Username is required</div>
              <div class="error" v-if="!$v.form.username.alphaNum">Username can only contain letters and numbers</div>
              <div class="error" v-if="!$v.form.username.minLength">Username must have at least {{$v.form.username.$params.minLength.min}} letters.</div>
              <div class="error" v-if="!$v.form.username.maxLength">Username is too long. It can contains maximium {{$v.form.username.$params.maxLength.max}} letters.</div>
            </div>
          </div>

          <div class="form-group">
            <!-- 사용자 이메일 주소 입력 -->
            <label for="emailAddress">Email address</label>
            <input type="email" class="form-control" id="emailAddress" v-model="form.emailAddress">
            <!-- 사용자 이메일 입력 오류 메시지 -->
            <div class="field-error" v-if="$v.form.emailAddress.$dirty">
                <div class="error" v-if="!$v.form.emailAddress.required">Email address is required</div>
                <div class="error" v-if="!$v.form.emailAddress.email">This is not a valid email address</div>
                <div class="error" v-if="!$v.form.emailAddress.maxLength">Email address is too long. It can contains maximium {{$v.form.emailAddress.$params.maxLength.max}} letters.</div>
              </div>
          </div>

          <div class="form-group">
            <!-- 사용자 비밀번호 입력 -->
            <label for="password">Password</label>
            <input type="password" class="form-control" id-="password" v-model="form.pasword">
            <!-- 사용자 비밀번호 입력 오류 메시지 -->
            <div class="field-error" v-if="$v.form.password.$dirty">
                <div class="error" v-if="!$v.form.password.required">Password is required</div>
                <div class="error" v-if="!$v.form.password.minLength">Password is too short. It can contains at least {{$v.form.password.$params.minLength.min}} letters.</div>
                <div class="error" v-if="!$v.form.password.maxLength">Password is too long. It can contains maximium {{$v.form.password.$params.maxLength.max}} letters.</div>
              </div>
          </div>

          <!-- 가입하기 버튼 -->
          <button type="submit" class="btn btn-primary btn-block">Create account</button>
          <p class="accept-terms text-muted">By clicking “Create account”, you agree to our <a href="#">terms of service</a> and <a href="#">privacy policy</a>.</p>
          <p class="text-center text-muted">Already have an account? <a href="/login">Sign in</a></p>
        </form>
      </div>
    </div>
    <footer class="footer">
      <span class="copyright">&copy; 2018 TaskAgile.com</span>
      <ul class="footer-links list-inline float-right">
          <li class="list-inline-item"><a href="#">About</a></li>
          <li class="list-inline-item"><a href="#">Terms of Service</a></li>
          <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
          <li class="list-inline-item"><a href="https://github.com/taskagile/vuejs.spring-boot.mysql" target="_blank">GitHub</a></li>
        </ul>
    </footer>
  </div>
</template>

<script>
import { required, email, minLength, maxLength, alphaNum } from 'vuelidate/lib/validators'
import registrationService from '@/serviecs/registration'

export default {
  name: 'RegisterPage',
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
.container {
  max-width: 900px;
}

.register-form {
  margin-top: 50px;
  max-width: 320px;
}

.logo-wrapper {
  text-align: center;
  margin-bottom: 40px;

  .tagline {
    line-height: 180%;
    color: #666;
  }

  .logo {
    max-width: 150px;
    margin: 0 auto;
  }
}

.register-form {
  .form-group label {
    font-weight: bold;
    color: #555;
  }

  .accept-terms {
    margin: 20px 0 40px 0;
  }
}

.footer {
  width: 100%;
  font-size: 13px;
  color: #666;
  line-height: 40px;
  border-top: 1px solid #ddd;
  margin-top: 50px;
  .list-inline-item {
    margin-right: 10px;
  }
  a {
    color: #666;
  }
}
</style>
