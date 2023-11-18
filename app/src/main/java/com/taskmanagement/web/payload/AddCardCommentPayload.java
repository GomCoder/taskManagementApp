package com.taskmanagement.web.payload;

import com.taskmanagement.domain.application.commands.AddCardCommentCommand;
import com.taskmanagement.domain.model.card.CardId;

public class AddCardCommentPayload {
  private String comment;

  public AddCardCommentCommand toCommand(CardId cardId) {
    return new AddCardCommentCommand(cardId, comment);
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
