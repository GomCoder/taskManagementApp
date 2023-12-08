package com.taskmanagement.domain.application.commands;

/**
 * 팀 생성 커멘드 클래스
 */
public class CreateTeamCommand extends  UserCommand {
  private final String name;

  public CreateTeamCommand(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
