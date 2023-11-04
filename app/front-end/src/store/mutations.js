export default {
  /**
   * 사용자의 이름, 사용자가 접근할 수 있는 모든 팀과 /api/me 요청 결과를 활용하여 Update 하는 메서드
   * @param state
   * @param data
   */
  updateMyData (state, data) {
    state.user.name = data.user.name
    state.user.authenticated = true
    state.teams = data.teams
    state.boards = data.boards
  },
  logout (state) {
    state.user.name = ''
    state.user.authenticated = false
    state.teams = []
    state.boards = []
  },
  /**
   * 새로 생성된 팀을 추가하는 메서드
   * @param state
   * @param team
   */
  addTeam (state, team) {
    state.teams.push(team)
  },
  /**
   * 새로 생성된 보드를 추가하는 메서드
   * @param state
   * @param board
   */
  addBoard (state, board) {
    state.boards.push(board)
  }
}
