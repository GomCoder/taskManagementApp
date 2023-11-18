<template>
  <div class="fileinput-button">
    <font-awesome-icon :icon="icon" class="icon" v-if="icon"/>{{ label }}
    <input type="file" :id="id" name="file" multiple>
  </div>
</template>

<script>
import $ from 'jquery'
import 'jquery-ui/ui/widget'
import 'blueimp-file-upload/js/jquery.fileupload'
import 'blueimp-file-upload/js/jquery.iframe-transport'
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Uploader',
  props: ['id', 'url', 'icon', 'label'],
  watch: {
    url () {
      if (!this.url) {
        return
      }

      $('#' + this.id).fileupload({
        url: this.url,
        dataType: 'json',
        add: (e, data) => {
          this.$emit('uploading', data.files[0])
          data.submit()
        },
        fail: (e, data) => {
          this.$emit('failed', data._response.jqXHR.responseJSON.message)
        },
        done: (e, data) => {
          this.$emit('uploaded', data.result)
        },
        progress: (e, data) => {
          // const progress = parseInt(data.loaded / data.total * 100, 10)
          const progress = `${Math.round((data.loaded / data.total) * 100)}%`
          this.$emit('progress', progress)
        }
      })
    }
  }
}

</script>

<style scoped lang="scss">
.icon {
  margin-right: .5rem;
}
</style>
