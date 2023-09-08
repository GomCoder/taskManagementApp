/**
 * 회원 가입이 성공할 때, 회원 가입이 실패할 때의 테스트를 위한 Mock
 */
export default {
  register(detail) {
    return new Promise((resolve, reject) => {
      detail.emailAddress == 'sunny@test.com' ? resolve({result: '성공'}) :  reject(new Error('이미 존재하는 사용자입니다.'))
    })
  }
}
