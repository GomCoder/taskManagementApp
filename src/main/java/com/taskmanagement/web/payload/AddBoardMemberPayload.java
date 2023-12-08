package com.taskmanagement.web.payload;

import com.taskmanagement.domain.application.commands.AddBoardMemberCommand;
import com.taskmanagement.domain.model.board.BoardId;

/**
 * 보드에 멤버 추가에 전송되는 데이터
 */
public class AddBoardMemberPayload {
  private String usernameOrEmailAddress;

  public AddBoardMemberCommand toCommand(BoardId boardId) {
    return new AddBoardMemberCommand(boardId, usernameOrEmailAddress);
  }

  public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
    this.usernameOrEmailAddress = usernameOrEmailAddress;
  }
}
