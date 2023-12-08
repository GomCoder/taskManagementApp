import Vue from 'vue'
import Vuex from 'vuex'
import * as getters from './getters'
import * as actions from './actions'
import mutations from './mutations'
import createLogger from 'vuex/dist/logger'

Vue.use(Vuex)

// 전역으로 사용할 변수들 설정
const state = {
  user: {
    name: null,
    authenticated: false
  },
  /**
   * teams: {id, name}
   */
  teams: [],
  /**
   * boards: {id, name, description, teamId}
   */
  boards: []
}
export default new Vuex.Store({
  state,
  getters,
  mutations,
  actions,
  plugins: process.env.NODE_ENV !== 'production' ? [createLogger()] : []
})
