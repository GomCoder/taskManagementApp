<script>
export default {
  name: 'PageFooter'
}
</script>

<template>
  <footer class="footer">
    <span class="copyright">&copy; 한양사이버대학교 응용소프트웨어공학과 김동원</span>
    <ul class="footer-links list-inline float-right">
      <li class="list-inline-item"><router-link to="/about">{{ $t("pageFooter.about") }}</router-link></li>
      <li class="list-inline-item"><a href="#">{{ $t("pageFooter.termOfService") }}</a></li>
      <li class="list-inline-item"><a href="#">{{ $t("pageFooter.privacyPolicy") }}</a></li>
      <li class="list-inline-item"><a href="https://github.com/taskagile/vuejs.spring-boot.mysql" target="_blank">GitHub</a></li>
    </ul>
  </footer>
</template>

<style lang="scss" scoped>
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
