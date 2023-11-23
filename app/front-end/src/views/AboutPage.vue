<template>
  <div>
  <PageHeader />
  <h5>About Page...</h5>
  <PageFooter />
  </div>
</template>

<script>
import PageHeader from '@/components/PageHeader.vue'
import PageFooter from '@/components/PageFooter.vue'

export default {
  name: 'AboutPage',
  components: {
    PageHeader,
    PageFooter
  }
}

</script>

<style scoped lang="scss">

</style>
