package com.taskmanagement.domain.model.card.events;

import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.model.card.Card;


public class CardAddedEvent extends DomainEvent {
  private Card card;

  public CardAddedEvent(Object source, Card card) {
    super(source);
    this.card = card;
  }

  public Card getCard() {
    return card;
  }
}
