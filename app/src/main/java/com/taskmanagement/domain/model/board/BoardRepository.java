package com.taskmanagement.domain.model.board;

import com.taskmanagement.domain.model.user.UserId;

import java.util.List;

public interface BoardRepository {
  /**
   * 사용자가 작성하거나 가입한 보드를 포함하여 사용자가 구성원인 보드를 찾는다.
   * @param userId
   * @return
   */
  List<Board> findBoardByMembership(UserId userId);

  /**
   * 아이디로 보드 찾기
   * @param boardId
   * @return
   */
  Board findById(BoardId boardId);

  /**
   * 보드 저장하기
   * @param board
   */
  void save(Board board);
}
