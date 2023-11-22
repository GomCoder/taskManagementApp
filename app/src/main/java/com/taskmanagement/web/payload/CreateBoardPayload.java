package com.taskmanagement.web.payload;

import com.taskmanagement.domain.application.commands.CreateBoardCommand;
import com.taskmanagement.domain.model.team.TeamId;

public class CreateBoardPayload {
  private String name;
  private String description;
  private long teamId;

  public CreateBoardCommand toCommand() {
    System.out.println("CreateBoardCommand(): " + name + " / " + description + " / " + new TeamId(teamId).value());
    return new CreateBoardCommand(name, description, new TeamId(teamId));
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setTeamId(long teamId) {
    this.teamId = teamId;
  }
}
