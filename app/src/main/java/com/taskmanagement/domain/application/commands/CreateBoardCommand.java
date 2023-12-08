package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.team.TeamId;

/**
 * 보드 생성 커멘드 클래스
 */
public class CreateBoardCommand extends UserCommand {
  private final String name;
  private final String description;
  private final TeamId teamId;

  public CreateBoardCommand(String name, String description, TeamId teamId) {
    this.name = name;
    this.description = description;
    this.teamId = teamId;
    System.out.println("CreateBoardCommand()생성자 호출: " + this.name + ", " + this.description + ", " + this.teamId);
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
