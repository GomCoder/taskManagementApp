package com.taskmanagement.web.results;

import com.taskmanagement.domain.model.card.Card;
import org.springframework.http.ResponseEntity;

/**
 * 카드 추가에 대한 결과
 */
public class AddCardResult {
  public static ResponseEntity<ApiResult> build(Card card) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", card.getId().value())
      .add("title", card.getTitle())
      .add("position", card.getPosition());
    return Result.ok(apiResult);
  }
}
