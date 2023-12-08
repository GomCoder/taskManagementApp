import meService from '@/services/me'

// 로그아웃, 내 정보 조회, 팀 추가, 보드 추가에 대한 서비스
export const logout = ({ commit }) => {
  commit('logout')
}
export const getMyData = ({ commit }) => {
  meService.getMyData().then(data => {
    commit('updateMyData', data)
  })
}

export const addTeam = ({ commit }, team) => {
  commit('addTeam', team)
}

export const addBoard = ({ commit }, board) => {
  commit('addBoard', board)
}
