package com.taskmanagement.domain.model.activity;

import com.taskmanagement.domain.model.board.events.BoardCreatedEvent;
import com.taskmanagement.domain.model.board.events.BoardMemberAddedEvent;

public class BoardActivities {
 public static Activity from(BoardCreatedEvent event) {
   String detail = ActivityDetail.blank()
     .add("boardName", event.getBoardName())
     .toJson();
   return Activity.from(event.getUserId(), event.getBoardId(), ActivityType.Add_BOARD, detail, event.getIpAddress());
 }

 public static Activity from(BoardMemberAddedEvent event) {
   String detail = ActivityDetail.blank()
     .add("memberUserId", event.getMemberUserId().value())
     .add("memberName", event.getMemberName())
     .toJson();
   return Activity.from(event.getUserId(), event.getBoardId(), ActivityType.ADD_BOARD_MEMBER, detail, event.getIpAddress());
 }
}
