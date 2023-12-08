package com.taskmanagement.domain.model.card.events;

import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.common.event.TriggeredBy;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.card.CardId;

import java.io.Serializable;

/**
 * 카드 도메인 이벤트
 */
public abstract class CardDomainEvent extends DomainEvent implements Serializable {

  private static final long serialVersionUID = -153577182336419756L;
  private final CardId cardId;
  private final String cardTitle;
  private final BoardId boardId;

  public CardDomainEvent(CardId cardId, String cardTitle, BoardId boardId, TriggeredBy triggeredBy) {
    super(triggeredBy);
    this.cardId = cardId;
    this.cardTitle = cardTitle;
    this.boardId = boardId;
  }

  public CardId getCardId() {
    return cardId;
  }

  public String getCardTitle() {
    return cardTitle;
  }

  public BoardId getBoardId() {
    return boardId;
  }
}
