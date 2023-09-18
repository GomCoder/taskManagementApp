package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.CreateBoardCommand;
import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.user.UserId;

import java.util.List;

public interface BoardService {
  /**
   * 사용자가 작성하거나 가입한 보드를 포함하여 사용자가 구성원인 보드를 찾는다.
   * @param userId
   * @return
   */
  List<Board> findBoardsByMembership(UserId userId);

  /**
   * 새로운 보드를 생성한다.
   * @param command
   * @return
   */
  Board createBoard(CreateBoardCommand command);
}
