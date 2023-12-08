// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })

module.exports =
{
  /**
   * 서버 포트에 대한 설정
   */
  devServer: {
    port: 3000,
    proxy: {
      '/api/*': {
        target: 'http://localhost:8080'
      },
      '/rt/*': {
        target: 'http://localhost:8080'
      },
      '/local-file/*': {
        target: 'http://localhost:8080'
      }
    }
  },
  /**
   * 웹팩을 이용하여 CSS 사용을 위한 설정
   */
  configureWebpack: {
    entry: {
      app: './src/main.js',
      style: [
        'bootstrap/dist/css/bootstrap.min.css',
        'blueimp-file-upload/css/jquery.fileupload.css',
        'noty/lib/noty.css',
        'noty/lib/themes/relax.css'
      ]
    }
  }
}
