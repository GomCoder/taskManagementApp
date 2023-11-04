package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.CardService;
import com.taskmanagement.domain.common.security.CurrentUser;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.domain.model.user.SimpleUser;
import com.taskmanagement.web.payload.AddCardPayload;
import com.taskmanagement.web.payload.ChangeCardPositionsPayload;
import com.taskmanagement.web.results.AddCardResult;
import com.taskmanagement.web.results.ApiResult;
import com.taskmanagement.web.results.Result;
import com.taskmanagement.web.updater.CardUpdater;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CardApiController {
  private final CardService cardService;
  private final CardUpdater cardUpdater;

  public CardApiController(CardService cardService, CardUpdater cardUpdater) {
    this.cardService = cardService;
    this.cardUpdater = cardUpdater;
  }

  @PostMapping("/api/cards")
  public ResponseEntity<ApiResult> addCard(@RequestBody AddCardPayload payload, @CurrentUser SimpleUser currentUser) {
    Card card = cardService.addCard(payload.toCommand(currentUser.getUserId()));
    cardUpdater.onCardAdded(payload.getBoardId(), card);
    return AddCardResult.build(card);
  }

  @PostMapping("/api/cards/positions")
  public ResponseEntity<ApiResult> changeCardPositions(@RequestBody ChangeCardPositionsPayload payload) {
    cardService.changePositions(payload.toCommand());
    return Result.ok();
  }
}
