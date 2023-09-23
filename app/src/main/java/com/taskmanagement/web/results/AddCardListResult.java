package com.taskmanagement.web.results;

import com.taskmanagement.domain.model.cardlist.CardList;
import org.springframework.http.ResponseEntity;

public class AddCardListResult {
  public static ResponseEntity<ApiResult> build(CardList cardList) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", cardList.getId().value())
      .add("name", cardList.getName());
    return Result.ok(apiResult);
  }
}
