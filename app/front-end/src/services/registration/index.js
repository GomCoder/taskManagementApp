import axios from 'axios'
import errorParser from '@/utils/error-parser'
export default
{
  /**
   * Register a new user(새로운 사용자 가입 데이터 검증)
   * @param {Object} detail
   * @returns resolve, reject
   */
  register (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/registrations', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
