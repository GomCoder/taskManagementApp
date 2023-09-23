package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.CreateTeamCommand;
import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.user.UserId;

import java.util.List;

public interface TeamService {
  /**
   * 사용자가 만든 팀 찾기
   * @param userId
   * @return
   */
  List<Team> findTeamsByUserId(UserId userId);

  /**
   * 아이디로 팀 찾기
   * @param teamId
   * @return
   */
  Team findById(TeamId teamId);

  /**
   * 새로운 팀 생성
   * @param command
   * @return
   */
  Team createTeam(CreateTeamCommand command);

}
