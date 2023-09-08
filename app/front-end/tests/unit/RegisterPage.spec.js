//Jest 회원 가입 페이지에 대한 단위 테스트(회원 가입 폼 요소들의 존재 여부 검증)
import { mount } from '@vue/test-utils'
import RegisterPage from '@/views/RegisterPage.vue'
import VueRouter from 'vue-router'

// const localVue = createLocalVue()
// localVue.use(VueRouter)
// localVue.use(Vuelidate)
// const router = new VueRouter()

//registrationService의 mock
jest.mock('@/services/registration')

describe('Test for RegisterPage', () => {
  /**
   * 변수 선언
   */
  let wrapper
  let fieldUsername //사용자 이름
  let fieldEmailAddress //사용자 이메일 주소
  let fieldPassword //사용자 비밀번호
  let buttonSubmit //가입하기 버튼
  // let registerSpy

  /**
   * 변수 초기화
   *
   */
  beforeEach(()=> {
    wrapper = mount(RegisterPage, {
      // localVue,
      // router
    })

    fieldUsername = wrapper.find('#username')
    fieldEmailAddress = wrapper.find('#emailAddress')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type-="submit"]')
    // registerSpy = jest.spyOn(registrationService, 'register')
  })

  afterAll(() => {
    jest.restoreAllMocks()
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
    expect(fieldUsername.element.value).toEqual('')
    //사용자 이메일 입력 요소 존재 여부 검증
    expect(fieldEmailAddress.element.value).toEqual('')
    //사용자 비밀번호 입력 요소 존재 여부 검즘
    expect(fieldPassword.element.value).toEqual('')
    //폼 제출 버튼 존재 여부 검증
    expect(buttonSubmit.text()).toEqual('가입하기')
  })

  /**
   * 사용자 이름, 이메일 주소, 비밀번호
   * RegisterPage의 데이터 모델의 초기값 검증 테스트.
   */
  it('RegisterPage의 데이터 모델의 초기값 검증 테스트.', ()=> {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  /**
   * 폼의 입력과 데이터 모델의 바인딩 검증 테스트
   */
  it('폼의 입력과 데이터 모델의 바인딩 검증 테스트', () => {
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
  it('제출 핸들러의 존재 여부 확인 테스트', () => {
    const spyFn = jest.spyOn(wrapper.vm, 'submitForm')
    buttonSubmit.trigger('submit')
    expect(spyFn).toBeCalled()
  })

  // /**
  //  * 성공적인 회원 가입 검증 테스트
  //  */
  // it('성공적인 회원 가입 검증 테스트', async () => {
  //   expect.assertions(2)
  //   const stub = jest.fn()
  //   wrapper.vm.$router.push = stub
  //   wrapper.vm.form.username = 'sunny'
  //   wrapper.vm.form.emailAddress = 'sunny@test.com'
  //   wrapper.vm.form.password = '1234'
  //   wrapper.vm.submitForm()
  //   expect(registerSpy).toBeCalled()
  //   await wrapper.vm.$nextTick()
  //   expect(stub).toHaveBeenCalledWith({name: 'LoginPage'})
  // })
  //

})


