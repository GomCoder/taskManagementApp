//Jest 로그인 페이지에 대한 단위 테스트 파일
import { mount } from '@vue/test-utils'
import LoginPage from '@/views/LoginPage.vue'

describe('Test for LoginPage', () => {
  it('LoginPage 해당 내용을 렌더링한다.', () => {
    const wrapper = mount(LoginPage)
    expect(wrapper.find('h1').text()).toEqual('Task Management')
  })
})
