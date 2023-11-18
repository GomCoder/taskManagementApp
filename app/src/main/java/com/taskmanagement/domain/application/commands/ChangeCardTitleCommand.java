package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.card.CardId;

public class ChangeCardTitleCommand extends UserCommand {
  private final CardId cardId;
  private final String title;

  public ChangeCardTitleCommand(CardId cardId, String title) {
    this.cardId = cardId;
    this.title = title;
  }

  public CardId getCardId() {
    return cardId;
  }

  public String getTitle() {
    return title;
  }
}
