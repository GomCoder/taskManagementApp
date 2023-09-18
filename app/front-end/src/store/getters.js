export const user = state => state.user

export const hasBoards = state => {
  return state.boards.length > 0
}

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
