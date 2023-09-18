<template>
  <div class="logo-wrapper">
    <img src="@/images/logo.png" alt="로고 이미지" class="logo">
    <div class="tagline">{{ $t("logo.tagLine") }}</div>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Logo'
}
</script>

<style lang="scss" scoped>
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
</style>
