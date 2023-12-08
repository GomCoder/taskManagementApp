package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.board.BoardId;
/**
 * 보드에 멤버를 추가하는 커멘드 클래스
 */
public class AddBoardMemberCommand extends UserCommand {
 
  private BoardId boardId;
  private String usernameOrEmailAddress;
 
  public AddBoardMemberCommand(BoardId boardId, String usernameOrEmailAddress) {
    this.boardId = boardId;
    this.usernameOrEmailAddress = usernameOrEmailAddress;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public String getUsernameOrEmailAddress() {
    return usernameOrEmailAddress;
  }
}
