package com.taskmanagement.domain.model.card;

import com.taskmanagement.domain.model.cardlist.CardListId;

/**
 * 카드의 위치 정보
 */
public class CardPosition {
  private long cardListId;
  private long cardId;
  private int position;

  public CardListId getCardListId() {
    return new CardListId(cardListId);
  }

  public CardId getCardId() {
    return new CardId(cardId);
  }

  public int getPosition() {
    return position;
  }

  public void setCardListId(long cardListId) {
    this.cardListId = cardListId;
  }

  public void setCardId(long cardId) {
    this.cardId = cardId;
  }

  public void setPosition(int position) {
    this.position = position;
  }
}
