package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.CardListService;
import com.taskmanagement.domain.common.security.CurrentUser;
import com.taskmanagement.domain.model.cardlist.CardList;
import com.taskmanagement.domain.model.user.SimpleUser;
import com.taskmanagement.web.payload.AddCardListPayload;
import com.taskmanagement.web.payload.ChangeCardListPositionsPayload;
import com.taskmanagement.web.results.AddCardListResult;
import com.taskmanagement.web.results.ApiResult;
import com.taskmanagement.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CardListApiController {
  private CardListService cardListService;

  public CardListApiController(CardListService cardListService) {
    this.cardListService = cardListService;
  }

  @PostMapping("/api/card-lists")
  public ResponseEntity<ApiResult> addCardList(@RequestBody AddCardListPayload payload, @CurrentUser SimpleUser currentUser) {
    CardList cardList = cardListService.addCardList(payload.toCommand(currentUser.getUserId()));
    return AddCardListResult.build(cardList);
  }

  @PostMapping("/api/card-lists/positions")
  public ResponseEntity<ApiResult> changeCardListPositions(@RequestBody ChangeCardListPositionsPayload payload) {
    cardListService.changePositions(payload.toCommand());
    return Result.ok();
  }
}
