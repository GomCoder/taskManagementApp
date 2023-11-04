/**
 * User 정보 가져오기 위한 변수
 * @param state
 * @returns {{authenticated: boolean, name: null}}
 */
export const user = state => state.user
/**
 * 보드 가져오기 위한 변수
 * @param state
 * @returns {boolean}
 */
export const hasBoards = state => {
  return state.boards.length > 0
}
/**
 * 개인 보드 가져오기 위한 변수
 * @param state
 * @returns {*[]}
 */
export const personalBoards = state => {
  return state.boards.filter(board => board.teamId === 0)
}

/**
 * 팀 보드 가져오기 위한 변수
 * @param state
 * @returns {*[]}
 */
export const teamBoards = state => {
  const teams = []
  state.teams.forEach(team => {
    teams.push({
      id: team.id,
      name: team.name,
      board: state.boards.filter(board => board.teamId === team.id)
    })
  })
  return teams
}
