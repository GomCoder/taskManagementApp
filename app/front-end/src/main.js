import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import Vuelidate from 'vuelidate'
import { library as faLibrary } from '@fortawesome/fontawesome-svg-core'
import { faHome, faSearch, faPlus, faEllipsisH, faUserPlus, faListUl } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { i18n } from './i18n'
import eventBus from '@/event-bus'
import realTimeClient from '@/real-time-client'

// 부트스트랩 axios 설정
axios.defaults.baseURL = '/api'
axios.defaults.headers.common.Accept = 'application/json'
axios.interceptors.response.use(
  response => response,
  (error) => {
    return Promise.reject(error)
  }
)
// 데이터 검증 활성화
Vue.use(Vuelidate)

// fontAwesome 설정
faLibrary.add(faHome, faSearch, faPlus, faEllipsisH, faUserPlus, faListUl)
Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false
Vue.prototype.$bus = eventBus
Vue.prototype.$rt = realTimeClient

new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app')
