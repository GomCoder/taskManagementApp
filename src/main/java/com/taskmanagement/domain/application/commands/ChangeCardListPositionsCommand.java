package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.cardlist.CardListPosition;

import java.util.List;

/**
 * 카드 리스트 위치 변경에 대한 커멘트 클래스
 */
public class ChangeCardListPositionsCommand extends UserCommand {
  private final BoardId boardId;
  private final List<CardListPosition> cardListPositions;

  public ChangeCardListPositionsCommand(BoardId boardId, List<CardListPosition> cardListPositions) {
    this.boardId = boardId;
    this.cardListPositions = cardListPositions;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public List<CardListPosition> getCardListPositions() {
    return cardListPositions;
  }
}
