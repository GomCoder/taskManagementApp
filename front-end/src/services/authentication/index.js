import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 사용자 로그인 요청에 대한 인증 POST 요청
   * @param detail
   * @returns {Promise<unknown>}
   */
  authenticate (detail) {
    // eslint-disable-next-line promise/param-names
    return new Promise((resolve, reject) => {
      axios.post('/authentications', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
