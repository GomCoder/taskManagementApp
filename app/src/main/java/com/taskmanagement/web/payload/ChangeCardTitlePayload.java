package com.taskmanagement.web.payload;

import com.taskmanagement.domain.application.commands.ChangeCardTitleCommand;
import com.taskmanagement.domain.model.card.CardId;

public class ChangeCardTitlePayload {
  private String title;

  public ChangeCardTitleCommand toCommand(long cardId) {
    return new ChangeCardTitleCommand(new CardId(cardId), title);
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
