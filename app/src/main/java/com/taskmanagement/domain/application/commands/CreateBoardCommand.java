package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.team.TeamId;

public class CreateBoardCommand extends UserCommand {
  private final String name;
  private final String description;
  private final TeamId teamId;

  public CreateBoardCommand(String name, String description, TeamId teamId) {
    this.name = name;
    this.description = description;
    this.teamId = teamId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public TeamId getTeamId() {
    return teamId;
  }
}
