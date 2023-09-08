import axios from 'axios'

/**
 * 회원 가입에 필요한 상세 정보를 POST 요청으로 바디에 담아 백엔드로 전송함
 * 성공: 호출한 곳에 응답을 반환
 * 실패: 에러와 함께 reject 호출
 */
export default {
  register (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/registrations', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(error)
      })
    })
  }
}
