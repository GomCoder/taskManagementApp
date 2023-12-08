package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.cardlist.CardListId;

/**
 * 카드 리스트 삭제 커멘드 클래스
 */
public class DeleteCardListCommand extends UserCommand {
  private final CardListId cardListId;

  public DeleteCardListCommand(CardListId cardListId) {
    this.cardListId = cardListId;

  }

  public CardListId getCardListId() {
    return cardListId;
  }
}
