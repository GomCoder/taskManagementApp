package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.card.CardId;

/**
 * 카드 삭제 커멘드 클래스
 */
public class DeleteCardCommand extends UserCommand{
  private final CardId cardId;

  public DeleteCardCommand(CardId cardId) {
    this.cardId = cardId;
  }

  public CardId getCardId() {
    return cardId;
  }
}
