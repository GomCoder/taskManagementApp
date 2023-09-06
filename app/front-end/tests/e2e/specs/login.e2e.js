// For authoring Nightwatch tests, see
// https://nightwatchjs.org/guide
//NightWatch 로그인 페이지에 대한 e2e 단위 테스트 파일
module.exports = {
  'login test': function(browser) {
    browser
      .url(process.env.VUE_DEV_SERVER_URL + 'login')
      .waitForElementVisible('#app', 5000)
      .assert.textContains('h1', 'Task Management')
      .end()
  }
}
