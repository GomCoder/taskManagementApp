const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })

module.exports = {
  // 개발 서버 포트 설정
  devServer: {
    port: 3000,
    proxy: {
      '/api/*' :{
        target: 'http://localhost:8080'
      }
    }
  },
  // 웹팩을 이용한 UI 구성에 필요한 부트스트랩 설정
  configureWebpack: {
    entry : {
      app: '/src/main.js',
      style: [
        'bootstrap/dist/css/bootstrap.min.css'
      ]
    }
  }
}
