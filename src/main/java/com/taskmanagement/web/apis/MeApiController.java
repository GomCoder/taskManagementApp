package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.BoardService;
import com.taskmanagement.domain.application.TeamService;
import com.taskmanagement.domain.application.UserService;
import com.taskmanagement.domain.common.security.CurrentUser;
import com.taskmanagement.domain.common.security.TokenManager;
import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.user.SimpleUser;
import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.web.results.ApiResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.taskmanagement.web.results.MyDataResult;

import java.util.List;

/**
 * 사용자 정보, 실시간처리, 토큰 생성 API 컨트롤러
 */
@Controller
public class MeApiController {
  private final String realTimeServerUrl;
  private final TeamService teamService;
  private final BoardService boardService;
  private final UserService userService;
  private final TokenManager tokenManager;

  public MeApiController(@Value(value = "${app.real-time-server-url}") String realTimeServerUrl,
                         TeamService teamService,
                         UserService userService,
                         BoardService boardService,
                         TokenManager tokenManager) {
    this.realTimeServerUrl = realTimeServerUrl;
    this.teamService = teamService;
    this.userService = userService;
    this.boardService = boardService;
    this.tokenManager = tokenManager;
  }

  /**
   * 현재 사용자 정보 조회 API
   * @param currentUser
   * @return
   */
  @GetMapping("/api/me")
  public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
    User user = userService.findById(currentUser.getUserId());
    List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
    List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());
    String realTimeToken = tokenManager.jwt(currentUser.getUserId());
    System.out.println("getMyData() 호출");
    return MyDataResult.build(user, teams, boards, realTimeServerUrl, realTimeToken);
  }
}
