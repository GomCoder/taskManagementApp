package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.team.TeamId;

public class DeleteBoardCommand extends UserCommand {
  private final BoardId boardId;

  public DeleteBoardCommand (BoardId boardId) {
    this.boardId = boardId;
  }

  public BoardId getBoardId() {
    return boardId;
  }
}
