package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.attachment.AttachmentId;
import com.taskmanagement.domain.model.attachment.AttachmentManagement;
import com.taskmanagement.domain.model.card.CardId;
import org.springframework.web.multipart.MultipartFile;

public class RemoveCardAttachmentCommand extends UserCommand{
  private final CardId cardId;
  private final AttachmentId attachmentId;

  public RemoveCardAttachmentCommand(long cardId,long attachmentId) {
    this.cardId = new CardId(cardId);
    this.attachmentId = new AttachmentId(attachmentId);

  }
  public CardId getCardId() {
    return cardId;
  }
}
