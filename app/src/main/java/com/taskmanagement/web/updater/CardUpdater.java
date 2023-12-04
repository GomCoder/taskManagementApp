package com.taskmanagement.web.updater;

import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.utils.JsonUtils;
import com.taskmanagement.web.socket.SubscriptionHub;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CardUpdater {
  public void onCardAdded(BoardId boardId, Card card) {
    Map<String, Object> cardData = new HashMap<>();
    cardData.put("id", card.getId().value());
    cardData.put("title", card.getTitle());
    cardData.put("cardListId", card.getCardListId().value());
    cardData.put("position", card.getPosition());

    Map<String, Object> update = new HashMap<>();
    update.put("type", "cardAdded");
    update.put("card", cardData);

    SubscriptionHub.send("/board/" + boardId.value(), JsonUtils.toJson(update));
  }
}
