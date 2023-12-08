package com.taskmanagement.domain.model.activity;

import com.taskmanagement.domain.model.attachment.event.CardAttachmentAddedEvent;
import com.taskmanagement.domain.model.attachment.event.CardAttachmentRemovedEvent;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.domain.model.card.events.CardAddedEvent;
import com.taskmanagement.domain.model.card.events.CardDeletedEvent;
import com.taskmanagement.domain.model.card.events.CardDescriptionChangedEvent;
import com.taskmanagement.domain.model.card.events.CardTitleChangedEvent;
import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.utils.IpAddress;

/**
 * 카드 활동 이력
 */
public class CardActivities {
  /**
   * 댓글 추가하기
   * @param card
   * @param userId
   * @param comment
   * @param ipAddress
   * @return
   */
 public static Activity from(Card card, UserId userId, String comment, IpAddress ipAddress) {
   String detail = ActivityDetail.blank()
     .add("comment", comment)
     .toJson();
     return Activity.from(userId, card.getId(), card.getBoardId(), ActivityType.ADD_COMMENT, detail, ipAddress);
 }

  /**
   * 카드 추가하기
   * @param event
   * @return
   */
 public static Activity from(CardAddedEvent event) {
   String detail = ActivityDetail.blank()
     .add("cardTitle", event.getCardTitle())
     .toJson();
   return Activity.from(event.getUserId(), event.getCardId(), event.getBoardId(), ActivityType.ADD_CARD, detail, event.getIpAddress());
 }

  /**
   * 첨부파일 추가하기
   * @param event
   * @return
   */
 public static Activity from(CardAttachmentAddedEvent event) {
   String detail = ActivityDetail.blank()
     .add("cardTitle", event.getCardTitle())
     .add("attachmentId", event.getAttachmentId().value())
     .add("fileName", event.getFileName())
     .toJson();
   System.out.println("Attachment.detail: " + detail);
   return Activity.from(event.getUserId(), event.getCardId(), event.getBoardId(), ActivityType.ADD_ATTACHMENT, detail, event.getIpAddress());
 }

  /**
   * 첨부파일 삭제하기
   * @param event
   * @return
   */
  public static Activity from(CardAttachmentRemovedEvent event) {
    String detail = ActivityDetail.blank()
      .add("cardTitle", event.getCardTitle())
      .add("attachmentId", event.getAttachmentId().value())
      .add("fileName", event.getFileName())
      .toJson();
    System.out.println("Attachment.detail: " + detail);
    return Activity.from(event.getUserId(), event.getCardId(), event.getBoardId(), ActivityType.REMOVE_ATTACHMENT, detail, event.getIpAddress());
  }

  /**
   * 카드 삭제하기
   * @param event
   * @return
   */
  public static Activity from(CardDeletedEvent event) {
    String detail = ActivityDetail.blank()
      .add("cardTitle", event.getCardTitle())
      .toJson();
    return Activity.from(event.getUserId(), event.getCardId(), event.getBoardId(), ActivityType.DELETE_CARD, detail, event.getIpAddress());
  }

  /**
   * 카드 설명 수정 하기
   * @param event
   * @return
   */
 public static Activity from(CardDescriptionChangedEvent event) {
   String detail = ActivityDetail.blank()
     .add("cardTitle", event.getCardTitle())
     .add("newDescription", event.getNewDescription())
     .add("oldDescription", event.getOldDescription())
     .toJson();
   return Activity.from(event.getUserId(), event.getCardId(), event.getBoardId(), ActivityType.CHANGE_CARD_DESCRIPTION, detail, event.getIpAddress());
 }

  /**
   * 카드 제목 수정하기
   * @param event
   * @return
   */
 public static Activity from(CardTitleChangedEvent event) {
   String detail = ActivityDetail.blank()
     .add("newTitle", event.getNewTitle())
     .add("oldTitle", event.getOldTitle())
     .toJson();
     return Activity.from(event.getUserId(), event.getCardId(), event.getBoardId(), ActivityType.CHANGE_CARD_TITLE, detail, event.getIpAddress());
 }
}
