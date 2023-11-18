package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.cardlist.CardListId;

public class AddCardCommand extends UserCommand{
  private final CardListId cardListId;

  private final String title;
  private final int position;

  public AddCardCommand(CardListId cardListId, String title, int position) {
    this.cardListId = cardListId;
    this.title = title;
    this.position = position;
  }

  public CardListId getCardListId() {
    return cardListId;
  }

  public String getTitle() {
    return title;
  }

  public int getPosition() {
    return position;
  }
}
