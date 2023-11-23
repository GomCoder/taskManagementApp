import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 새로운 카드 추가
   * @param detail 카드 추가 사항
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
   * 카드 삭제하기
   * @param cardId 카드 아이디
   * @returns {Promise<unknown>}
   */
  deleteCard (cardId) {
    return new Promise((resolve, reject) => {
      axios.delete('/cards/' + cardId).then(({ data }) => {
        resolve(data)
        console.log('deleteCard(): ' + cardId)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 카드 가져오기
   * @param cardId 카드 아이디
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
   * @param cardId 카드 아이디
   * @param title 카드 제목
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
   * @param cardId 카드 아이디
   * @param description 카드 설명
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
   * @param positionChanges 카드 변경된 위치
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
   * @param cardId 카드 아이디
   * @param comment 댓글 내용
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
   * @param cardId 카드 아이디
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
  /**
   * 카드에 첨부파일 추가하기
   * @param cardId 카드 아이디
   * @returns {Promise<unknown>}
   */
  getCardAttachments (cardId) {
    return new Promise((resolve, reject) => {
      axios.get('/cards/' + cardId + '/attachments').then(({ data }) => {
        resolve(data)
        console.log('getCardAttachments(): ' + cardId)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 카드의 첨부파일 삭제하기
   * @param cardId
   * @returns {Promise<unknown>}
   */
  deleteCardAttachments (cardId) {
    return new Promise((resolve, reject) => {
      axios.delete('/cards/' + cardId + '/attachments').then(({ data }) => {
        resolve(data)
        console.log('getCardAttachments(): ' + cardId)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  }
}
