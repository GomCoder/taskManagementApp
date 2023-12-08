package com.taskmanagement.domain.model.card.events;

import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.card.Card;

import java.io.Serializable;

/**
 * 카드 삭제 이벤트
 */
public class CardDeletedEvent extends CardDomainEvent implements Serializable {

  private static final long serialVersionUID = -3188733301855842887L;

  public CardDeletedEvent(Card card, TriggeredBy triggeredBy) {
    super(card.getId(), card.getTitle(),  card.getBoardId(), triggeredBy);
  }

  @Override
  public String toString() {
    return "CardDeletedEvent{}" +
      "carId= " + getCardId() +
      ", cardTitle= '" + getCardTitle() + '\'' +
      +'}';
  }
}
