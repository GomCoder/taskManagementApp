package com.taskmanagement.domain.model.cardlist.events;

import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.board.events.BoardDomainEvent;
import com.taskmanagement.domain.model.cardlist.CardList;
import com.taskmanagement.domain.model.cardlist.CardListId;

import java.io.Serializable;

public class CardListAddedEvent extends BoardDomainEvent implements Serializable {
  private static final long serialVersionUID = 8414088332493939423L;
  private final CardListId cardListId;
  private final String cardListName;

  public CardListAddedEvent(CardList cardList, TriggeredBy triggeredBy) {
    super(cardList.getBoardId(), triggeredBy);
    this.cardListId = cardList.getId();
    this.cardListName = cardList.getName();
  }

  public CardListId getCardListId() {
    return cardListId;
  }

  public String getCardListName() {
    return cardListName;
  }

  @Override
  public String toString() {
    return "CardListAddedEvent{" +
      "cardListId=" + cardListId +
      ", cardListName='" + cardListName + '\'' +
      '}';
  }
}
