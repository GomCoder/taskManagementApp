package com.taskmanagement.domain.model.board.events;

import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserId;

public class BoardMemberAddedEvent extends BoardDomainEvent {
  private final UserId memberUserId;
  private final String memberName;

  public BoardMemberAddedEvent(BoardId boardId, User addedUser, TriggeredBy triggeredBy) {
    super(boardId, triggeredBy);
    this.memberUserId = addedUser.getId();
    this.memberName = addedUser.getFirstName() + " " + addedUser.getLastName();
  }

  public UserId getMemberUserId() {
    return memberUserId;
  }

  public String getMemberName() {
    return memberName;
  }

  @Override
  public String toString() {
    return "BoardMemberAddedEvent{" +
      "boardId= " + getBoardId() +
      "memberUserId=" + memberUserId +
      ", memberName='" + memberName + '\'' +
      '}';
  }
}
