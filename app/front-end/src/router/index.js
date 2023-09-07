import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from '@/views/LoginPage.vue'
import RegisterPage from '@/views/RegisterPage.vue'

// 페이지 경로 설정 파일
const routes = [
  // 로그인 페이지 경로 설정
  {
    path: '/login',
    name: 'LoginPage',
    component: LoginPage
  },
  // 회원 가입 페이지 경로 설정
  {
    path: '/register',
    name: 'RegisterPage',
    component: RegisterPage
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
