package com.taskmanagement.domain.model.board.events;

import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.user.User;

public class BoardMemberAddedEvent extends DomainEvent {
  private BoardId boardId;
  private User user;

  public BoardMemberAddedEvent(Object source, BoardId boardId, User user) {
    super(source);
    this.boardId = boardId;
    this.user = user;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public User getUser() {
    return user;
  }
}
