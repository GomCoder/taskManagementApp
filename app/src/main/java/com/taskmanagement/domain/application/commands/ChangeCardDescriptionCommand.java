package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.card.CardId;

/**
 * 카드 설명 변경에 대한 커멘드 클래스
 */
public class ChangeCardDescriptionCommand extends UserCommand {
  private final CardId cardId;
  private final String description;

  public ChangeCardDescriptionCommand(CardId cardId, String description) {
    this.cardId = cardId;
    this.description = description;
  }

  public CardId getCardId() {
    return cardId;
  }

  public String getDescription() {
    return description;
  }
}
