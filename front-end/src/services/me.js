import axios from 'axios'
import errorParser from '@/utils/error-parser'
import eventBus from '@/event-bus'

export default {
  /**
   * 현재 사용자의 이름, 보드, 팀 조회를 위한 GET 요청
   * @returns {Promise<unknown>}
   */
  getMyData () {
    return new Promise((resolve, reject) => {
      axios.get('/me').then(({ data }) => {
        resolve(data)
        eventBus.$emit('myDataFetched', data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 로그아웃을 위한 POST 요청
   * @returns {Promise<unknown>}
   */
  signOut () {
    return new Promise((resolve, reject) => {
      axios.post('/me/logout').then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
