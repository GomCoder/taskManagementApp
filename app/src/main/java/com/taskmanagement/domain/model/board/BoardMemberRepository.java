package com.taskmanagement.domain.model.board;

public interface BoardMemberRepository {
  /**
   * 보드 멤버 저장하기
   * @param boardMember
   */
  void save(BoardMember boardMember);
}
