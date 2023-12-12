package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.TeamService;
import com.taskmanagement.domain.application.commands.CreateTeamCommand;
import com.taskmanagement.domain.application.commands.DeleteTeamCommand;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.team.TeamId;
import com.taskmanagement.web.payload.CreateTeamPayload;
import com.taskmanagement.web.results.ApiResult;
import com.taskmanagement.web.results.CreateTeamResult;
import com.taskmanagement.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

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

  /**
   * 팀 삭제 API
   * @param teamId
   * @param request
   * @return
   */
  @DeleteMapping("/api/teams/{teamId}")
  public ResponseEntity<ApiResult> deleteTeam(@PathVariable long teamId,
                                              HttpServletRequest request) {
    DeleteTeamCommand command = new DeleteTeamCommand(new TeamId(teamId));
    addTriggeredBy(command, request);

    teamService.deleteTeam(command);
    return Result.ok();
  }
}
