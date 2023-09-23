import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 새로운 카드 리스트 추가
   * @param detail
   * @returns {Promise<unknown>}
   */
  add (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/card-list', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 카드 위치 변경
   * @param positionChanges
   * @returns {Promise<unknown>}
   */
  changePositions (positionChanges) {
    return new Promise((resolve, reject) => {
      axios.post('/card_lists/positions', positionChanges).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
