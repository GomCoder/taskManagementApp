package com.taskmanagement.domain.model.board.events;

import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.board.BoardId;

public abstract class BoardDomainEvent extends DomainEvent {

  private final BoardId boardId;

  public BoardDomainEvent(BoardId boardId, TriggeredBy triggeredBy) {
    super(triggeredBy);
    this.boardId = boardId;
  }

  public BoardId getBoardId() {
    return boardId;
  }
}
