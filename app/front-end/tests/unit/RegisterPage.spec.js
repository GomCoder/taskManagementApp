//Jest 회원 가입 페이지에 대한 단위 테스트(회원 가입 폼 요소들의 존재 여부 검증)
import { mount } from '@vue/test-utils'
import RegisterPage from '@/views/RegisterPage.vue'
import {onBeforeMount} from "vue";



describe('Test for RegisterPage', () => {
  /**
   * 변수 선언
   */
  let wrapper
  let fieldUsername //사용자 이름
  let fieldEmailAddress //사용자 이메일 주소
  let fieldPassword //사용자 비밀번호
  let buttonSubmit //가입하기 버튼

  /**
   * 변수 초기화
   *
   */
  beforeEach(()=> {
    wrapper = mount(RegisterPage)
    fieldUsername = wrapper.find('#username')
    fieldEmailAddress = wrapper.find('#emailAddress')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type-="submit"]')
  })

  /**
   * 로고 이미지, 태그 라인, 사용자 이름, 이메일 주소, 비밀번호, 가입하기 버튼
   * RegisterPage의 요소 존재 여부 테스트
   */
  it('RegisterPage의 요소 존재 여부 테스트.', () => {
    //로고 이미지 요소 존재 여부 검증
    expect(wrapper.find('.logo').attributes('src')).toEqual('/static/images/logo.png')
    //tagline 요소 존재 여부 검증
    expect(wrapper.find('.tagline').text()).toEqual('오픈 소스 일정 관리 툴')
    //사용자 이름 입력 요소 존재 여부 검증
    expect(fieldUsername.text()).toEqual('')
    //사용자 이메일 입력 요소 존재 여부 검증
    expect(fieldEmailAddress.text()).toEqual('')
    //사용자 비밀번호 입력 요소 존재 여부 검즘
    expect(fieldPassword.text()).toEqual('')
    //폼 제출 버튼 존재 여부 검증
    expect(buttonSubmit.text()).toEqual('가입하기')
  })

  /**
   * 사용자 이름, 이메일 주소, 비밀번호
   * RegisterPage의 데이터 모델의 초기값 검증 테스트.
   */
  it('RegisterPage의 데이터 모델의 초기값 검증 테스트.', ()=> {
    expect(fieldUsername.text()).toEqual('')
    expect(fieldEmailAddress.text()).toEqual('')
    expect(fieldPassword.text()).toEqual('')
  })

  /**
   * 폼의 입력과 데이터 모델의 바인딩 검증 테스트
   */
  it('폼의 입력과 데이터 모델의 바인딩 검증 테스트',() => {
    const username = 'sunny'
    const emailAddress = 'sunny@test.com'
    const password = '1234'

    fieldUsername.value = username
    fieldEmailAddress.value = emailAddress
    fieldPassword.value = password

    expect(fieldUsername.value).toEqual('sunny')
    expect(fieldEmailAddress.value).toEqual('sunny@test.com')
    expect(fieldPassword.value).toEqual('1234')
  })

  /**
   * 제출 핸들러의 존재 여부 확인 테스트
   */
  it('제출 핸들러의 존재 여부 확인 테스트',() => {
    const spyFn = jest.spyOn(wrapper.vm, 'submitForm')
    buttonSubmit.trigger('submit')
    expect(spyFn).toBeCalled()
  })
})


