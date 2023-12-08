package com.taskmanagement.domain.model.card.events;

import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.card.Card;

import java.io.Serializable;

/**
 * 카드 설명 변경 이벤트
 */
public class CardDescriptionChangedEvent extends CardDomainEvent implements Serializable {
  private static final long serialVersionUID = 5907104789563362338L;
  private final String newDescription;
  private final String oldDescription;

  public CardDescriptionChangedEvent(Card card, String oldDescription, TriggeredBy triggeredBy) {
    super(card.getId(), card.getTitle(), card.getBoardId(), triggeredBy);
    this.newDescription = card.getDescription();
    this.oldDescription = oldDescription;
  }

  public String getNewDescription() {
    return newDescription;
  }

  public String getOldDescription() {
    return oldDescription;
  }

  @Override
  public String toString() {
    return "CardDescriptionChangedEvent{" +
      "cardId= " +  getCardId() +
      "newDescription='" + newDescription + '\'' +
      ", oldDescription='" + oldDescription + '\'' +
      '}';
  }
}
