package com.taskmanagement.domain.model.board;

import com.taskmanagement.domain.model.user.UserId;

import java.util.List;

/**
 * 보드 레포지토리 - 사용자 아이디로 보드 조회, 보드 아이디로 보드 조회, 
 *                 보드 저장, 보드 삭제하기
 */
public interface BoardRepository {
  /**
   * 사용자가 작성하거나 가입한 보드를 포함하여 사용자가 구성원인 보드를 찾는다.
   * @param userId
   * @return
   */
  List<Board> findBoardsByMembership(UserId userId);

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

  /**
   * 보드 삭제하기
   */
  Board deleteBoard(BoardId boardId);
}
