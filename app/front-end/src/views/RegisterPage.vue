<!-- 회원가입 페이지 컴포넌트 -->
<script>
export default {
  name: 'RegisterPage',
  methods: {
    submitForm() {
      this.$emit('submit', this.form)
    }
  }
}
</script>

<template>
  <!-- 회원 가입 페이지의 전반적인 구성 요소-->
  <div class="container">
    <!-- 페이지의 주요 바디 부분 -->
    <div class="row" justify-content-center>
      <!-- 회원가입 Form -->
      <div class="register-from">
        <!-- 로고 -->
        <div class="logo-wrapper">
          <img class="logo" src="/static/images/logo.png" alt="로고 이미지">
          <div class="tagline">오픈 소스 일정 관리 툴</div>
        </div>
        <!-- 폼 그룹 -->
        <form @submit.prevent="submitForm">
          <!-- 사용자 이름 입력 -->
          <div class="form-group">
            <label id="username"></label>
          </div>
          <!-- 사용자 이메일 입력 -->
          <div class="form-group">
            <label id="emailAddress"></label>
          </div>
          <!-- 사용자 비밀번호 입력 -->
          <div class="form-group">
            <label id="password"></label>
          </div>
          <!-- 가입하기 버튼 -->
          <button type-="submit" class="btn btn-outline-primary btn-block" onclick="submitForm">가입하기</button>
        </form>
      </div>
    </div>
    <!-- 푸터 부분-->
    <div class="footer">
      <!--copy right -->
      <span class="copyright"></span>
      <!-- 푸터에 사용될 링크 -->
      <ul class="footer-links" list-inline float-right></ul>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  max-width: 900px;
}

.register-from {
  margin-top: 50px;
  max-width: 320px;
}

.logo-wrapper {
  margin-bottom: 40px;
}

.footer {
  width: 100%;
  line-height: 40px;
  margin-top: 50px;
}

</style>
