//Jest 회원 가입 페이지에 대한 단위 테스트(회원 가입 폼 요소들의 존재 여부 검증)
import { mount } from '@vue/test-utils'
import RegisterPage from '@/views/RegisterPage.vue'

describe('Test for RegisterPage', () => {
  it('RegisterPage의 요소 존재 여부 테스트.', () => {
    const wrapper = mount(RegisterPage)
    //로고 이미지 요소 존재 여부 검증
    expect(wrapper.find('.logo').attributes('src')).toEqual('/static/images/logo.png')
    //tagline 요소 존재 여부 검증
    expect(wrapper.find('.tagline').text()).toEqual('오픈 소스 일정 관리 툴')
    //사용자 이름 입력 요소 존재 여부 검증
    expect(wrapper.find('#username').text()).toEqual('')
    //사용자 이메일 입력 요소 존재 여부 검증
    expect(wrapper.find('#emailAddress').text()).toEqual('')
    //사용자 비밀번호 입력 요소 존재 여부 검즘
    expect(wrapper.find('#password').text()).toEqual('')
    //폼 제출 버튼 존재 여부 검증
    expect(wrapper.find('form button[type-="submit"]').text()).toEqual('가입하기')
  })
})
