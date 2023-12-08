package com.taskmanagement.web.results;

import com.taskmanagement.domain.model.card.Card;
import org.springframework.http.ResponseEntity;

/**
 * 카드 처리에 대한 결과
 */
public class CardResult {

  public static ResponseEntity<ApiResult> build(Card card) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", card.getId().value())
      .add("boardId", card.getBoardId().value())
      .add("cardListId", card.getCardListId().value())
      .add("title", card.getTitle())
      .add("description", card.getDescription());
    return Result.ok(apiResult);
  }
}
