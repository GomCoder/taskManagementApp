package com.taskmanagement.domain.model.card.events;

import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.card.Card;

import java.io.Serializable;


public class CardAddedEvent extends CardDomainEvent implements Serializable {
  private static final long serialVersionUID = 116600169794867090L;

  public CardAddedEvent(Card card, TriggeredBy triggeredBy) {
    super(card.getId(), card.getTitle(), card.getBoardId(), triggeredBy);
    System.out.println("CardAddedEvent()" + this.toString());
  }

  @Override
  public String toString() {
    return "CardAddedEvent{}" +
      "cardId= " + getCardId() +
      ", cardTitle= '" + getCardTitle() + '\'' +
      '}';
  }
}
