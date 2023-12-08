import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 팀 생성하기 POST 요청
   * @param detail 팀 세부사항
   * @returns {Promise<unknown>}
   */
  create (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/teams', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
