package com.taskmanagement.web.results;

import com.taskmanagement.domain.model.team.Team;
import org.springframework.http.ResponseEntity;

/**
 * 팀 생성 처리에 대한 결과
 */
public class CreateTeamResult {
  public static ResponseEntity<ApiResult> build(Team team) {
    System.out.println("-------------- CreateTeamResult.build(): " + team.getId().value());
    ApiResult apiResult = ApiResult.blank()
      .add("id", team.getId().value())
      .add("name", team.getName());
    return Result.ok(apiResult);
  }
}
