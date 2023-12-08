package com.taskmanagement.domain.model.board;

import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserId;

import java.util.List;

/**
 * 보드 멤버 레포지토리 - 보드 아이디로 멤버 조회, 보드 멤버 저장, 보드에 사용자(멤버) 추가
 */
public interface BoardMemberRepository {
  /**
   * 보드로 멤버 찾기
   * @param boardId
   * @return
   */
  List<User> findMembers(BoardId boardId);
  /**
   * 보드 멤버 저장하기
   * @param boardMember
   */
  void save(BoardMember boardMember);
  /**
   * 보드에 사용자 추가하기
   */
  void add(BoardId boardId, UserId userId);
}
