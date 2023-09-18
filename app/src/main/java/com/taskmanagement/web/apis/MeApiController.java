package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.BoardService;
import com.taskmanagement.domain.application.TeamService;
import com.taskmanagement.domain.common.security.CurrentUser;
import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.user.SimpleUser;
import com.taskmanagement.web.results.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.taskmanagement.web.results.MyDataResult;

import java.util.List;

@Controller
public class MeApiController {
  private TeamService teamService;
  private BoardService boardService;

  public MeApiController(TeamService teamService, BoardService boardService) {
    this.teamService = teamService;
    this.boardService = boardService;
  }

  @GetMapping("/api/me")
  public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
    List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
    List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());
    return MyDataResult.build(currentUser, teams, boards);
  }
}
