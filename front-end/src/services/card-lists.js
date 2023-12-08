import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 새로운 카드 리스트 추가 POST 요청
   * @param detail
   * @returns {Promise<unknown>}
   */
  add (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/card-lists', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 카드 위치 변경 POST 요청
   * @param positionChanges
   * @returns {Promise<unknown>}
   */
  changePositions (positionChanges) {
    return new Promise((resolve, reject) => {
      axios.post('/card-lists/positions', positionChanges).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * 카드 리스트 삭제 DELETE 요청
   * @param cardListId
   * @returns {Promise<unknown>}
   */
  deleteCardList (cardListId) {
    return new Promise((resolve, reject) => {
      axios.delete('/card-lists/' + cardListId).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
