package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.CreateTeamCommand;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.team.TeamId;
import com.taskmanagement.domain.model.user.UserId;

import java.util.List;

/**
 * 팀 서비스 - 사용자 아이디로 팀 조회, 팀 아이디로 팀 조회, 새로운 팀 생성 
 */
public interface TeamService {
  /**
   * 사용자가 만든 팀 찾기
   * @param userId 사용자 아이디
   * @return 팀 정보
   */
  List<Team> findTeamsByUserId(UserId userId);

  /**
   * 아이디로 팀 찾기
   * @param teamId 팀 아이디
   * @return 팀 정보
   */
  Team findById(TeamId teamId);

  /**
   * 새로운 팀 생성
   * @param command 새로운 팀 생성 명령
   * @return 팀 정보
   */
  Team createTeam(CreateTeamCommand command);

}
