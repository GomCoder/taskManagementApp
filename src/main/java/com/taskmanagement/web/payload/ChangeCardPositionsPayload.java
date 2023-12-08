package com.taskmanagement.web.payload;

import com.taskmanagement.domain.application.commands.ChangeCardPositionsCommand;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.card.CardPosition;

import java.util.List;

/**
 * 카드 위치 변경에 전송되는 데이터
 */
public class ChangeCardPositionsPayload {
  private long boardId;
  private List<CardPosition> cardPositions;

  public ChangeCardPositionsCommand toCommand() {
    return new ChangeCardPositionsCommand(new BoardId(boardId), cardPositions);
  }

  public void setBoardId(long boardId) {
    this.boardId = boardId;
  }

  public void setCardPositions(List<CardPosition> cardPositions) {
    this.cardPositions = cardPositions;
  }
}
