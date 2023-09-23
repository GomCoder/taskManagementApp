package com.taskmanagement.domain.model.board;

import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserId;

import java.util.List;

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
