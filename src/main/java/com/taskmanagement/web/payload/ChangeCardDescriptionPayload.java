package com.taskmanagement.web.payload;

import com.taskmanagement.domain.application.commands.ChangeCardDescriptionCommand;
import com.taskmanagement.domain.model.card.CardId;

/**
 * 카드 설명 변경에 전송되는 데이터
 */
public class ChangeCardDescriptionPayload {
  private String description;

  public ChangeCardDescriptionCommand toCommand(long cardId) {
    return new ChangeCardDescriptionCommand(new CardId(cardId), description);
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
