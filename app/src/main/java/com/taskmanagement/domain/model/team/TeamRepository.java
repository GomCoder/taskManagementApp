package com.taskmanagement.domain.model.team;

import com.taskmanagement.domain.model.user.UserId;

import java.util.List;

public interface TeamRepository {
  /**
   * 만든 사용자에 의해 생성된 보드 조회
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
   * 팀 저장하기
   * @param team
   */
  void save(Team team);
}
