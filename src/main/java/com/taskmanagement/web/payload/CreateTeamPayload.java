package com.taskmanagement.web.payload;

import com.taskmanagement.domain.application.commands.CreateTeamCommand;

/**
 * 팀 생성에 전송되는 데이터
 */
public class CreateTeamPayload {
  private String name;

  public CreateTeamCommand toCommand() {
    return new CreateTeamCommand(name);
  }

  public void setName(String name) {
    this.name = name;
  }
}
