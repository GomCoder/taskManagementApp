package com.taskmanagement.domain.model.activity;

import com.taskmanagement.domain.model.cardlist.events.CardListAddedEvent;

/**
 * 카드 리스트 활동 이력
 */
public class CardListActivities {
  public static Activity from(CardListAddedEvent event) {
    String detail = ActivityDetail.blank()
      .add("cardListId", event.getCardListId().value())
      .add("cardListName", event.getCardListName())
      .toJson();
    return Activity.from(event.getUserId(), event.getBoardId(), ActivityType.Add_BOARD, detail, event.getIpAddress());
  }
}
