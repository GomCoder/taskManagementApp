package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.TeamService;
import com.taskmanagement.domain.application.commands.CreateTeamCommand;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.web.payload.CreateTeamPayload;
import com.taskmanagement.web.results.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.taskmanagement.web.results.CreateTeamResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 팀 API 컨트롤러
 */
@Controller
public class TeamApiController extends AbstractBaseController {
  private final TeamService teamService;

  public TeamApiController(TeamService teamService) {
    this.teamService = teamService;
  }

  /**
   * 팀 생성 API
   * @param payload
   * @param request
   * @return
   */
  @PostMapping("/api/teams")
  public ResponseEntity<ApiResult> createTeam(@RequestBody CreateTeamPayload payload,
                                              HttpServletRequest request) {
    CreateTeamCommand command = payload.toCommand();
    addTriggeredBy(command, request);

    Team team = teamService.createTeam(command);
    return CreateTeamResult.build(team);
  }
}
