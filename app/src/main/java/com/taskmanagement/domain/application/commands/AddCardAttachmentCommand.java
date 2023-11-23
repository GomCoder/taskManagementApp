package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.card.CardId;
import org.springframework.web.multipart.MultipartFile;

public class AddCardAttachmentCommand extends UserCommand {
  private final CardId cardId;
  private final MultipartFile file;

  public AddCardAttachmentCommand(long cardId, MultipartFile file) {
    this.cardId = new CardId(cardId);
    this.file = file;
    System.out.println("AddCardAttachmentCommand() 호출: " + this.cardId.value() + " / " + this.file.getOriginalFilename());
  }

  public CardId getCardId() {
    return cardId;
  }

  public MultipartFile getFile() {
    return file;
  }

}
