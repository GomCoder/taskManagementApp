package com.taskmanagement.domain.model.activity;

import com.taskmanagement.domain.model.board.events.BoardCreatedEvent;
import com.taskmanagement.domain.model.board.events.BoardMemberAddedEvent;

/**
 * 보드 활동 이력 저장
 */
public class BoardActivities {
  /**
   * 보드 생성 이벤트 이력
   */
 public static Activity from(BoardCreatedEvent event) {
   String detail = ActivityDetail.blank()
     .add("boardName", event.getBoardName())
     .toJson();
   return Activity.from(event.getUserId(), event.getBoardId(), ActivityType.Add_BOARD, detail, event.getIpAddress());
 }
 /**
  * 보드에 멤버 추가 이벤트 이력
  */
 public static Activity from(BoardMemberAddedEvent event) {
   String detail = ActivityDetail.blank()
     .add("memberUserId", event.getMemberUserId().value())
     .add("memberName", event.getMemberName())
     .toJson();
   return Activity.from(event.getUserId(), event.getBoardId(), ActivityType.ADD_BOARD_MEMBER, detail, event.getIpAddress());
 }
}
