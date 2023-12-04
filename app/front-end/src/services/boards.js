import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 보드 생성하기
   * @param detail
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
  },
  /**
   * 보드에 맴버 추가하기
   * @param boardId
   * @param usernameOrEmailAddress
   * @returns {Promise<unknown>}
   */
  addMember (boardId, usernameOrEmailAddress) {
    return new Promise((resolve, reject) => {
      axios.post('/boards/' + boardId + '/members', { usernameOrEmailAddress }).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 보드 가져오기
   * @param boardId
   * @returns {Promise<unknown>}
   */
  getBoard (boardId) {
    return new Promise((resolve, reject) => {
      axios.get('/boards/' + boardId).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  /**
   * 보드 삭제하기
   * @param boardId
   * @returns {Promise<unknown>}
   */
  deleteBoard (boardId) {
    return new Promise((resolve, reject) => {
      axios.delete('/boards/' + boardId).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
