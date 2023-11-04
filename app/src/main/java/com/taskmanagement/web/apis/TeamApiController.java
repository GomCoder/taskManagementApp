package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.TeamService;
import com.taskmanagement.domain.common.security.CurrentUser;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.user.SimpleUser;
import com.taskmanagement.web.payload.CreateTeamPayload;
import com.taskmanagement.web.results.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.taskmanagement.web.results.CreateTeamResult;

@Controller
public class TeamApiController {
  private final TeamService teamService;

  public TeamApiController(TeamService teamService) {
    this.teamService = teamService;
  }

  @PostMapping("/api/teams")
  public ResponseEntity<ApiResult> createTeam(@RequestBody CreateTeamPayload payload,
                                              @CurrentUser SimpleUser currentUser) {
    Team team = teamService.createTeam(payload.toCommand(currentUser.getUserId()));
    return CreateTeamResult.build(team);
  }
}
