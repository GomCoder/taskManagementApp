package com.taskmanagement.domain.model.cardlist.events;

import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.model.cardlist.CardList;

public class CardListAddedEvent extends DomainEvent {
  private CardList cardList;

  public CardListAddedEvent(Object source, CardList cardList) {
    super(source);
    this.cardList = cardList;
  }

  public CardList getCardList() {
    return cardList;
  }
}
