package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.card.CardId;

/**
 * 카드에 댓글을 추가하는 커멘드 클래스
 */
public class AddCardCommentCommand extends UserCommand {
  private CardId cardId;
  private String comment;

  public AddCardCommentCommand(CardId cardId, String comment) {
    this.cardId = cardId;
    this.comment = comment;
  }

  public CardId getCardId() {
    return cardId;
  }

  public String getComment() {
    return comment;
  }
}
