package com.taskmanagement.domain.model.board.events;

import com.taskmanagement.domain.common.event.TriggeredBy;
import  com.taskmanagement.domain.model.board.Board;

import java.io.Serializable;

/**
 * 보드 생성 이벤트
 */
public class BoardCreatedEvent extends BoardDomainEvent implements Serializable {
  private static final long serialVersionUID = -2382719359002912153L;
  private final String boardName;

  public BoardCreatedEvent(Board board, TriggeredBy triggeredBy) {
    super(board.getId(), triggeredBy);
    this.boardName = board.getName();
  }

  public String getBoardName() {
    return boardName;
  }

  @Override
  public String toString() {
    return "BoardCreatedEvent{" +
      "boardId= " + getBoardId() +
      "boardName='" + boardName + '\'' +
      '}';
  }
}
