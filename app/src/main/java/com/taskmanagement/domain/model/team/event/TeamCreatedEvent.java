package com.taskmanagement.domain.model.team.event;

import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.model.team.Team;

public class TeamCreatedEvent extends DomainEvent {
  private Team team;

  public TeamCreatedEvent(Object source, Team team) {
    super(source);
    this.team = team;
  }

  public Team getTeam() {
    return team;
  }
}
