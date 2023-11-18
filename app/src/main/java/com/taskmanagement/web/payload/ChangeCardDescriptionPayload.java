package com.taskmanagement.web.payload;

import com.taskmanagement.domain.application.commands.ChangeCardDescriptionCommand;
import com.taskmanagement.domain.model.card.CardId;

public class ChangeCardDescriptionPayload {
  private String description;

  public ChangeCardDescriptionCommand toCommand(long cardId) {
    return new ChangeCardDescriptionCommand(new CardId(cardId), description);
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
