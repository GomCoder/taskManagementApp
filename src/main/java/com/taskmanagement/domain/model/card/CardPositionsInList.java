package com.taskmanagement.domain.model.card;

import com.taskmanagement.domain.model.cardlist.CardListId;

/**
 * 카드 리스트 안에서의 카드 위치 정보
 */
import java.util.List;
public class CardPositionsInList {
  private long cardListId;
  private List<CardPosition> cardPositions;

  public CardListId getCardListId() {
    return new CardListId(cardListId);
  }

  public void setCardListId(long cardListId) {
    this.cardListId = cardListId;
  }

  public List<CardPosition> getCardPositions() {
    return cardPositions;
  }

  public void setCardPositions(List<CardPosition> cardPositions) {
    this.cardPositions = cardPositions;
  }
}
