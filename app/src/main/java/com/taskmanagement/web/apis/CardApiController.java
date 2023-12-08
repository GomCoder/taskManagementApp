package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.CardService;
import com.taskmanagement.domain.application.commands.*;
import com.taskmanagement.domain.common.file.FileUrlCreator;
import com.taskmanagement.domain.model.activity.Activity;
import com.taskmanagement.domain.model.attachment.Attachment;
import com.taskmanagement.domain.model.attachment.AttachmentId;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.domain.model.card.CardId;
import com.taskmanagement.web.payload.*;
import com.taskmanagement.web.results.*;
import com.taskmanagement.web.updater.CardUpdater;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 카드 API 컨트롤러
 */
@Controller
public class CardApiController extends AbstractBaseController {
  private final CardService cardService;
  private final CardUpdater cardUpdater;
  private final FileUrlCreator fileUrlCreator;

  public CardApiController(CardService cardService,
                           CardUpdater cardUpdater,
                           FileUrlCreator fileUrlCreator) {
    this.cardService = cardService;
    this.cardUpdater = cardUpdater;
    this.fileUrlCreator = fileUrlCreator;
  }

  /**
   * 카드 추가 API
   * @param payload
   * @param request
   * @return
   */
  @PostMapping("/api/cards")
  public ResponseEntity<ApiResult> addCard(@RequestBody AddCardPayload payload,
                                           HttpServletRequest request) {
    AddCardCommand command = payload.toCommand();
    addTriggeredBy(command, request);

    Card card = cardService.addCard(command);
    cardUpdater.onCardAdded(payload.getBoardId(), card);
    return AddCardResult.build(card);
  }

  /**
   * 카드 위치 변경 API
   * @param payload
   * @param request
   * @return
   */
  @PostMapping("/api/cards/positions")
  public ResponseEntity<ApiResult> changeCardPositions(@RequestBody ChangeCardPositionsPayload payload,
                                                       HttpServletRequest request) {
    ChangeCardPositionsCommand command = payload.toCommand();
    addTriggeredBy(command, request);
    cardService.changePositions(command);
    return Result.ok();
  }

  /**
   * 카드 조회 API
   * @param cardId
   * @return
   */
  @GetMapping("/api/cards/{cardId}")
  public ResponseEntity<ApiResult> getCard(@PathVariable long cardId) {
    Card card = cardService.findById(new CardId(cardId));
    return CardResult.build(card);
  }

  /**
   * 카드 제목 수정 API
   * @param cardId
   * @param payload
   * @param request
   * @return
   */
  @PutMapping("/api/cards/{cardId}/title")
  public ResponseEntity<ApiResult> changeTitle(@PathVariable long cardId,
                                               @RequestBody ChangeCardTitlePayload payload,
                                               HttpServletRequest request) {
    ChangeCardTitleCommand command = payload.toCommand(cardId);
    addTriggeredBy(command, request);
    cardService.changeCardTitle(command);
    return Result.ok();
  }

  /**
   * 카드 설명 수정 API
   * @param cardId
   * @param payload
   * @param request
   * @return
   */
  @PutMapping("/api/cards/{cardId}/description")
  public ResponseEntity<ApiResult> changeDescription(@PathVariable long cardId,
                                                     @RequestBody ChangeCardDescriptionPayload payload,
                                                     HttpServletRequest request) {
    ChangeCardDescriptionCommand command = payload.toCommand(cardId);
    addTriggeredBy(command, request);
    cardService.changeCardDescription(command);
    return Result.ok();
  }

  /**
   * 카드 댓글 추가 API
   * @param cardId
   * @param payload
   * @param request
   * @return
   */
  @PostMapping("/api/cards/{cardId}/comments")
  public ResponseEntity<ApiResult> addCardComment(@PathVariable long cardId,
                                                  @RequestBody AddCardCommentPayload payload,
                                                  HttpServletRequest request) {
    AddCardCommentCommand command = payload.toCommand(new CardId(cardId));
    addTriggeredBy(command, request);
    Activity activity = cardService.addComment(command);
    return CommentActivityResult.build(activity);
  }

  /**
   * 카드 활동 이력 조회 API
   * @param cardId
   * @return
   */
  @GetMapping("/api/cards/{cardId}/activities")
  public ResponseEntity<ApiResult> getCardActivities(@PathVariable long cardId) {
    List<Activity> activities = cardService.findCardActivities(new CardId(cardId));
    return CardActivitiesResult.build(activities);
  }

  /**
   * 카드에 첨부파일 추가 API
   * @param cardId
   * @param file
   * @param request
   * @return
   */
  @PostMapping("/api/cards/{cardId}/attachments")
  public ResponseEntity<ApiResult> addAttachment(@PathVariable long cardId,
                                                 @RequestParam("file") MultipartFile file,
                                                 HttpServletRequest request) {
    AddCardAttachmentCommand command = new AddCardAttachmentCommand(cardId, file);
    addTriggeredBy(command, request);

    Attachment attachment = cardService.addAttachment(command);
    return AttachmentResult.build(attachment, fileUrlCreator);
  }


  /**
   * 카드  첨부파일 조회 API
   * @param cardId
   * @return
   */
  @GetMapping("/api/cards/{cardId}/attachments")
  public ResponseEntity<ApiResult> getAttachments(@PathVariable long cardId) {
    List<Attachment> attachments = cardService.getAttachments(new CardId(cardId));
    return AttachmentResults.build(attachments, fileUrlCreator);
  }

  /**
   * 카드 삭제 API
   * @param cardId
   * @param request
   * @return
   */
  @DeleteMapping("/api/cards/{cardId}")
  public ResponseEntity<ApiResult> deleteCard(@PathVariable long cardId,
                                              HttpServletRequest request) {
    DeleteCardCommand command = new DeleteCardCommand(new CardId(cardId));
    addTriggeredBy(command, request);

    cardService.deleteCard(command);
    return Result.ok();
  }

  /**
   * 카드 첨부파일 삭제 API
   * @param cardId
   * @param attachmentId
   * @return
   */
  @DeleteMapping("/api/cards/{cardId}/attachments/{attachmentId}")
  public ResponseEntity<ApiResult> removeAttachment(@PathVariable long cardId,
                                                    @PathVariable long attachmentId,
                                                    HttpServletRequest request) {
    RemoveCardAttachmentCommand command = new RemoveCardAttachmentCommand(cardId, attachmentId);
    addTriggeredBy(command, request);
    cardService.removeAttachment(new CardId(cardId), new AttachmentId(attachmentId));
    return Result.ok();
  }
}
