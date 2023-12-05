package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.CardListService;
import com.taskmanagement.domain.application.commands.AddCardListCommand;
import com.taskmanagement.domain.application.commands.ChangeCardListPositionsCommand;
import com.taskmanagement.domain.application.commands.DeleteCardCommand;
import com.taskmanagement.domain.application.commands.DeleteCardListCommand;
import com.taskmanagement.domain.model.cardlist.CardList;
import com.taskmanagement.domain.model.cardlist.CardListId;
import com.taskmanagement.web.payload.AddCardListPayload;
import com.taskmanagement.web.payload.ChangeCardListPositionsPayload;
import com.taskmanagement.web.results.AddCardListResult;
import com.taskmanagement.web.results.ApiResult;
import com.taskmanagement.web.results.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CardListApiController extends AbstractBaseController {
  private final CardListService cardListService;

  public CardListApiController(CardListService cardListService) {
    this.cardListService = cardListService;
  }

  /**
   * 카드 리스트 추가 API
   * @param payload
   * @param request
   * @return
   */
  @PostMapping("/api/card-lists")
  public ResponseEntity<ApiResult> addCardList(@RequestBody AddCardListPayload payload,
                                               HttpServletRequest request) {
    AddCardListCommand command = payload.toCommand();
    addTriggeredBy(command, request);
    CardList cardList = cardListService.addCardList(command);
    return AddCardListResult.build(cardList);
  }

  /**
   * 카드 리스트 위치 변경 API
   * @param payload
   * @param request
   * @return
   */
  @PostMapping("/api/card-lists/positions")
  public ResponseEntity<ApiResult> changeCardListPositions(@RequestBody ChangeCardListPositionsPayload payload,
                                                           HttpServletRequest request) {
    ChangeCardListPositionsCommand command = payload.toCommand();
    addTriggeredBy(command, request);
    cardListService.changePositions(command);
    return Result.ok();
  }

  /**
   * 카드 리스트 삭제 API
   * @param cardListId
   * @param request
   * @return
   */
  @DeleteMapping("/api/card-lists/{cardListId}")
  public ResponseEntity<ApiResult> deleteCardList(@PathVariable long cardListId,
                                                  HttpServletRequest request) {
    try {
      DeleteCardListCommand command = new DeleteCardListCommand(new CardListId(cardListId));
      addTriggeredBy(command, request);
      cardListService.deleteCardList(command);
      return Result.ok();
    } catch (Exception e) {
      return Result.failure("카드 리스트 삭제 실패");
    }
  }
}
