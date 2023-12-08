package com.taskmanagement.domain.model.attachment.event;

import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.attachment.Attachment;
import com.taskmanagement.domain.model.attachment.AttachmentId;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.domain.model.card.events.CardDomainEvent;

import java.io.Serializable;

/**
 * 카드 첨부파일 추가 이벤트
 */
public class CardAttachmentAddedEvent  extends CardDomainEvent implements Serializable {
  private static final long serialVersionUID = 8233792662173384548L;
  private final String cardTitle;
  private final AttachmentId attachmentId;
  private final String fileName;

  public CardAttachmentAddedEvent(Card card, Attachment attachment, TriggeredBy triggeredBy) {
    super(card.getId(), card.getTitle(), card.getBoardId(), triggeredBy);
    this.cardTitle = card.getTitle();
    this.attachmentId = attachment.getId();
    this.fileName = attachment.getFileName();
  }

  public String getCardTitle() {
    return cardTitle;
  }

  public AttachmentId getAttachmentId() {
    return attachmentId;
  }

  public String getFileName() {
    return fileName;
  }

  @Override
  public String toString() {
    return "CardAttachmentAddedEvent{" +
      "cardTitle='" + cardTitle + '\'' +
      ", attachmentId=" + attachmentId +
      ", fileName='" + fileName + '\'' +
      '}';
  }
}
