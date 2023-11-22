package com.taskmanagement.domain.model.team.events;

import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.team.TeamId;

import java.io.Serializable;

public class TeamCreatedEvent extends DomainEvent implements Serializable {
  private static final long serialVersionUID = 3709781468895119505L;
  private final TeamId teamId;
  private final String teamName;

  public TeamCreatedEvent(Team team, TriggeredBy triggeredBy) {
    super(triggeredBy);
    this.teamId = team.getId();
    this.teamName = team.getName();
    System.out.println("TeamCreatedEvent()생성자 호출: " + this.teamId.value());
  }

  public TeamId getTeamId() {
    return teamId;
  }

  public String getTeamName() {
    return teamName;
  }

  @Override
  public String toString() {
    return "TeamCreatedEvent{" +
      "teamId=" + teamId +
      ", teamName='" + teamName + '\'' +
      '}';
  }
}
