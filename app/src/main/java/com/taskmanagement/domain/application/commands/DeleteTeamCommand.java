package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.team.TeamId;

public class DeleteTeamCommand extends UserCommand {
  private final TeamId teamId;

  public DeleteTeamCommand (TeamId teamId) {
    this.teamId = teamId;
  }

  public TeamId getTeamId() {
    return teamId;
  }
}
