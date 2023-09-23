package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.CreateBoardCommand;
import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.domain.model.user.UserNotFoundException;

import java.util.List;

public interface BoardService {
  /**
   * 사용자가 작성하거나 가입한 보드를 포함하여 사용자가 구성원인 보드를 찾는다.
   * @param userId
   * @return
   */
  List<Board> findBoardsByMembership(UserId userId);
  /**
   * 아이디로 보드 착기
   */
  Board findById(BoardId boardId);

  /**
   * 보드의 멤버 찾기
   * @param boardId
   * @return
   */
  List<User> findMembers(BoardId boardId);

  /**
   * 새로운 보드를 생성한다.
   * @param command
   * @return
   */
  Board createBoard(CreateBoardCommand command);

  /**
   * 보드 멤버 추가하기
   * @param boardId
   * @param usernameOrEmailAddress
   * @return
   * @throws UserNotFoundException
   */
  User addMember(BoardId boardId, String usernameOrEmailAddress) throws UserNotFoundException;
}
