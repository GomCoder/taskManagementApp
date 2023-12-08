package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.AddBoardMemberCommand;
import com.taskmanagement.domain.application.commands.CreateBoardCommand;
import com.taskmanagement.domain.application.commands.DeleteBoardCommand;
import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.domain.model.user.UserNotFoundException;

import java.util.List;

/**
 * 보드 서비스 - 보드 정보, 보드 조회, 보드 멤버, 보드 생성, 보드 멤버 추가, 보드 삭제 기능
 */
public interface BoardService {
  /**
   * 사용자가 작성하거나 가입한 보드를 포함하여 사용자가 구성원인 보드를 찾는다.
   * @param userId 사용자 아이디
   * @return 보드 정보
   */
  List<Board> findBoardsByMembership(UserId userId);
  /**
   * 아이디로 보드 찾기
   */
  Board findById(BoardId boardId);

  /**
   * 보드의 멤버 찾기
   * @param boardId 보드 아이디
   * @return 멤버 정보
   */
  List<User> findMembers(BoardId boardId);

  /**
   * 새로운 보드를 생성한다.
   * @param command 보드 생성 명령
   * @return 보드 정보
   */
  Board createBoard(CreateBoardCommand command);

  /**
   * 보드 멤버 추가하기
   * @param command 보드에 멤버 추가 명령
   * @return 멤버 정보
   * @throws UserNotFoundException 사용자를 찾지 못한 예외 발생
   */
  User addMember(AddBoardMemberCommand command) throws UserNotFoundException;

  /**
   * 보드 삭제하기
   */
  void deleteBoard(DeleteBoardCommand command);
}
