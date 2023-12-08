<!-- 페이지 Footer UI-->
<template>
  <footer class="footer">
    <span class="copyright">&copy; 한양사이버대학교 응용소프트웨어공학과 김동원</span>
    <ul class="footer-links list-inline float-right">
      <li class="list-inline-item"><router-link to="/about">{{ $t("pageFooter.about") }}</router-link></li>
      <li class="list-inline-item"><a href="#">{{ $t("pageFooter.termOfService") }}</a></li>
      <li class="list-inline-item"><a href="#">{{ $t("pageFooter.privacyPolicy") }}</a></li>
      <li class="list-inline-item"><a href="#" target="_blank">GitHub</a></li>
    </ul>
  </footer>
</template>

<script>
export default {
  name: 'PageFooter'
}
</script>

<style lang="scss" scoped>
.footer {
  width: 100%;
  font-size: 13px;
  color: #666;
  line-height: 40px;
  border-top: 1px solid #ddd;
  margin-top: 50px;
  padding: 9px 10px 8px;
  .list-inline-item {
    margin-right: 10px;
  }
  a {
    color: #666;
  }
}
</style>
