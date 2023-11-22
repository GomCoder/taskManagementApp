import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 새로운 카드 추가
   * @param detail
   * @returns {Promise<unknown>}
   */
  add (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/cards', detail).then(({ data }) => {
        resolve(data)
        console.log('add(): ' + detail)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 카드 가져오기
   * @param cardId
   * @returns {Promise<unknown>}
   */
  getCard (cardId) {
    return new Promise((resolve, reject) => {
      axios.get('/cards/' + cardId).then(({ data }) => {
        resolve(data)
        console.log('getCard(): ' + cardId)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 카드 제목 수정
   * @param cardId
   * @param title
   * @returns {Promise<unknown>}
   */
  changeCardTitle (cardId, title) {
    return new Promise((resolve, reject) => {
      axios.put('/cards/' + cardId + '/title', { title }).then(({ data }) => {
        resolve(data)
        console.log('changeCardTitle(): ' + cardId)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 카드 설명 수정
   * @param cardId
   * @param description
   * @returns {Promise<unknown>}
   */
  changeCardDescription (cardId, description) {
    return new Promise((resolve, reject) => {
      axios.put('/cards/' + cardId + '/description', { description }).then(({ data }) => {
        resolve(data)
        console.log('changeCardDescription(): ' + cardId)
      }).catch(error => {
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
      axios.post('/cards/positions', positionChanges).then(({ data }) => {
        resolve(data)
        console.log('changePositions(): ' + positionChanges)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 카드에 댓글 추가하기
   * @param cardId
   * @param comment
   * @returns {Promise<unknown>}
   */
  addCardComment (cardId, comment) {
    return new Promise((resolve, reject) => {
      axios.post('/cards/' + cardId + '/comments', { comment }).then(({ data }) => {
        resolve(data)
        console.log('addCardComment(): ' + cardId)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 카드의 활동 기록 가져오기
   * @param cardId
   * @returns {Promise<unknown>}
   */
  getCardActivities (cardId) {
    return new Promise((resolve, reject) => {
      axios.get('/cards/' + cardId + '/activities').then(({ data }) => {
        resolve(data)
        console.log('getCardActivities(): ' + cardId)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },
  getCardAttachments (cardId) {
    return new Promise((resolve, reject) => {
      axios.get('/cards/' + cardId + '/attachments').then(({ data }) => {
        resolve(data)
        console.log('getCardAttachments(): ' + cardId)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  }
}
