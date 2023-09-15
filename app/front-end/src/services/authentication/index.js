import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 사용자 로그인 요청에 대한 인증
   * @param detail
   * @returns {Promise<unknown>}
   */
  authenticate (detail) {
    // eslint-disable-next-line promise/param-names
    return new Promise((reslove, reject) => {
      axios.post('/authentications', detail).then(({ data }) => {
        reslove(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}