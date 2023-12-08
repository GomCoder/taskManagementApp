package com.taskmanagement.web.results;

import com.taskmanagement.domain.model.board.Board;
import org.springframework.http.ResponseEntity;

/**
 * 보드 생성처리에 대한 결과
 */
public class CreateBoardResult {
  public static ResponseEntity<ApiResult> build(Board board) {
    System.out.println("-------------- CreateBoardResult.build(): " + board.getTeamId().value());
    ApiResult apiResult = ApiResult.blank()
      .add("id", board.getId().value())
      .add("name", board.getName())
      .add("description", board.getDescription())
      .add("teamId", board.getTeamId().value());
    return Result.ok(apiResult);
  }
}
