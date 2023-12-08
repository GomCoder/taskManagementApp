package com.taskmanagement.domain.application.impl;

import com.taskmanagement.domain.application.TeamService;
import com.taskmanagement.domain.application.commands.CreateTeamCommand;
import com.taskmanagement.domain.common.event.DomainEventPublisher;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.team.TeamId;
import com.taskmanagement.domain.model.team.TeamRepository;
import com.taskmanagement.domain.model.team.events.TeamCreatedEvent;
import com.taskmanagement.domain.model.user.UserId;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 팀 서비스 구현
 */
@Service
@Transactional
public class TeamServiceImpl implements TeamService {
  private final TeamRepository teamRepository;
  private final DomainEventPublisher domainEventPublisher;

  public TeamServiceImpl(TeamRepository teamRepository, DomainEventPublisher domainEventPublisher) {
    this.teamRepository = teamRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  /**
   * 사용자 아이디로 팀 정보 조회
   */
  @Override
  public List<Team> findTeamsByUserId(UserId userId) {
    return teamRepository.findTeamsByUserId((userId));
  }

  /**
   * 팀 아이디로 팀 정보 조회 
   */
  @Override
  public Team findById(TeamId teamId) {
    return teamRepository.findById(teamId);
  }

  /**
   * 팀 생성
   */
  @Override
  public Team createTeam(CreateTeamCommand command) {
    Team team = Team.create(command.getName(), command.getUserId());
    teamRepository.save(team);
    domainEventPublisher.publish(new TeamCreatedEvent(team, command));
    System.out.println(team.getCreatedDate());
    return team;
  }
}
