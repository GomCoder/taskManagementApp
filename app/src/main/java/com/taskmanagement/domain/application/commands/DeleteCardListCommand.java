package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.cardlist.CardListId;

public class DeleteCardListCommand extends UserCommand {
  private final CardListId cardListId;

  public DeleteCardListCommand(CardListId cardListId) {
    this.cardListId = cardListId;

  }

  public CardListId getCardListId() {
    return cardListId;
  }
}
