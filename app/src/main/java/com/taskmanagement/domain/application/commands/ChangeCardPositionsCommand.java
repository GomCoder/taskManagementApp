package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.card.CardPosition;

import java.util.List;

public class ChangeCardPositionsCommand extends UserCommand {
  private final BoardId boardId;
  private final List<CardPosition> cardPositions;

  public ChangeCardPositionsCommand(BoardId boardId, List<CardPosition> cardPositions) {
    this.boardId = boardId;
    this.cardPositions = cardPositions;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public List<CardPosition> getCardPositions() {
    return cardPositions;
  }
}
