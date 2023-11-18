package com.taskmanagement.domain.application.commands;

public class CreateTeamCommand extends  UserCommand {
  private final String name;

  public CreateTeamCommand(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
