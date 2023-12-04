package com.taskmanagement.web.payload;


import com.taskmanagement.domain.application.commands.DeleteBoardCommand;
import com.taskmanagement.domain.model.board.BoardId;
public class DeleteBoardPayload {
  private final long boardId;

  public DeleteBoardPayload(long boardId) {
    this.boardId = boardId;
  }

  public long getBoardId() {
    return boardId;
  }

  public DeleteBoardCommand toCommend(BoardId boardId) {
    return new DeleteBoardCommand(boardId);
  }
}
