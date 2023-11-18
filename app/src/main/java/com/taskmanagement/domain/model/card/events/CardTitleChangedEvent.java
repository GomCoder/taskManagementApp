package com.taskmanagement.domain.model.card.events;

import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.card.Card;

import java.io.Serializable;

public class CardTitleChangedEvent extends CardDomainEvent implements Serializable {


  private static final long serialVersionUID = -8017056758344666121L;
  private final String newTitle;
  private final String oldTitle;

  public CardTitleChangedEvent(Card card, String oldTitle, TriggeredBy triggeredBy) {
    super(card.getId(), card.getTitle(), card.getBoardId(), triggeredBy);
    this.newTitle = card.getTitle();
    this.oldTitle = oldTitle;
  }

  public String getNewTitle() {
    return newTitle;
  }

  public String getOldTitle() {
    return oldTitle;
  }

  @Override
  public String toString() {
    return "CardTitleChangedEvent{" +
      "cardId= " + getCardId() +
      "newTitle='" + newTitle + '\'' +
      ", oldTitle='" + oldTitle + '\'' +
      '}';
  }
}
