import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 보드 생성하기
   * @param detail: 보드 세부사항
   * @returns {Promise<unknown>}
   */
  create (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/boards', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
