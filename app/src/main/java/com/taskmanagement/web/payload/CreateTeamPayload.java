package com.taskmanagement.web.payload;

import com.taskmanagement.domain.application.commands.CreateTeamCommand;
import com.taskmanagement.domain.model.user.UserId;

public class CreateTeamPayload {
  private String name;

  public CreateTeamCommand toCommand(UserId userId) {
    return new CreateTeamCommand(userId, name);
  }

  public void setName(String name) {
    this.name = name;
  }
}
