package com.taskmanagement.domain.model.attachment.event;

import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.attachment.Attachment;
import com.taskmanagement.domain.model.attachment.AttachmentId;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.domain.model.card.events.CardDomainEvent;

import java.io.Serializable;

public class CardAttachmentRemovedEvent extends CardDomainEvent implements Serializable {
  private static final long serialVersionUID = -6160887431580734456L;
  private final String cardTitle;
  private final AttachmentId attachmentId;
  private final String fileName;


  public CardAttachmentRemovedEvent(Card card, Attachment attachment, TriggeredBy triggeredBy) {
    super(card.getId(), card.getTitle(), card.getBoardId(),triggeredBy);
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
    return "CardAttachmentRemovedEvent{" +
      "cardTitle='" + cardTitle + '\'' +
      ", attachmentId=" + attachmentId +
      ", fileName='" + fileName + '\'' +
      '}';
  }
}
