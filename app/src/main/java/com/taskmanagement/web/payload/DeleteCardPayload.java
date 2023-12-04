package com.taskmanagement.web.payload;

import com.taskmanagement.domain.application.commands.DeleteCardCommand;
import com.taskmanagement.domain.model.card.CardId;

public class DeleteCardPayload {
  private final long cardId;

  public DeleteCardPayload(long cardId) {
    this.cardId = cardId;
  }

  public long getCardId() {
    return cardId;
  }

  public DeleteCardCommand toCommand(CardId cardId) {
    return new DeleteCardCommand(cardId);
  }
}
