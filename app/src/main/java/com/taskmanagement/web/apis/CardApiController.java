package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.CardService;
import com.taskmanagement.domain.application.commands.*;
import com.taskmanagement.domain.common.file.FileUrlCreator;
import com.taskmanagement.domain.model.activity.Activity;
import com.taskmanagement.domain.model.attachment.Attachment;
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

  @PostMapping("/api/cards")
  public ResponseEntity<ApiResult> addCard(@RequestBody AddCardPayload payload,
                                           HttpServletRequest request) {
    System.out.println("POST: addCard() 호출");
    AddCardCommand command = payload.toCommand();
    addTriggeredBy(command, request);

    Card card = cardService.addCard(command);
    cardUpdater.onCardAdded(payload.getBoardId(), card);
    return AddCardResult.build(card);
  }

  @PostMapping("/api/cards/positions")
  public ResponseEntity<ApiResult> changeCardPositions(@RequestBody ChangeCardPositionsPayload payload,
                                                       HttpServletRequest request) {
    System.out.println("POST: changeCardPositions() 호출");
    ChangeCardPositionsCommand command = payload.toCommand();
    addTriggeredBy(command, request);
    cardService.changePositions(command);
    return Result.ok();
  }

  @GetMapping("/api/cards/{cardId}")
  public ResponseEntity<ApiResult> getCard(@PathVariable long cardId) {
    System.out.println("GET: getCard() 호출");
    Card card = cardService.findById(new CardId(cardId));
    return CardResult.build(card);
  }

  @PutMapping("/api/cards/{cardId}/title")
  public ResponseEntity<ApiResult> changeTitle(@PathVariable long cardId,
                                               @RequestBody ChangeCardTitlePayload payload,
                                               HttpServletRequest request) {
    System.out.println("PUT: changeTitle() 호출");
    ChangeCardTitleCommand command = payload.toCommand(cardId);
    addTriggeredBy(command, request);
    cardService.changeCardTitle(command);
    return Result.ok();
  }
  @PutMapping("/api/cards/{cardId}/description")
  public ResponseEntity<ApiResult> changeDescription(@PathVariable long cardId,
                                                     @RequestBody ChangeCardDescriptionPayload payload,
                                                     HttpServletRequest request) {
    System.out.println("PUT: changeDescription() 호출");
    ChangeCardDescriptionCommand command = payload.toCommand(cardId);
    addTriggeredBy(command, request);
    cardService.changeCardDescription(command);
    return Result.ok();
  }

  @PostMapping("/api/cards/{cardId}/comments")
  public ResponseEntity<ApiResult> addCardComment(@PathVariable long cardId,
                                                  @RequestBody AddCardCommentPayload payload,
                                                  HttpServletRequest request) {
    System.out.println("POST: addCardComment() 호출");
    AddCardCommentCommand command = payload.toCommand(new CardId(cardId));
    addTriggeredBy(command, request);
    Activity activity = cardService.addComment(command);
    return CommentActivityResult.build(activity);
  }

  @GetMapping("/api/cards/{cardId}/activities")
  public ResponseEntity<ApiResult> getCardActivities(@PathVariable long cardId) {
    System.out.println("GET: getCardActivities() 호출");
    List<Activity> activities = cardService.findCardActivities(new CardId(cardId));
    return CardActivitiesResult.build(activities);
  }

  @PostMapping("/api/cards/{cardId}/attachments")
  public ResponseEntity<ApiResult> addAttachment(@PathVariable long cardId,
                                                 @RequestParam("file") MultipartFile file,
                                                 HttpServletRequest request) {
    System.out.println("POST: addAttachment() 호출");
    System.out.println("Attachment API- addAttachment() 호출: " + cardId);
    System.out.println("Attachment API- addAttachment() 호출: " + file.getName());
    AddCardAttachmentCommand command = new AddCardAttachmentCommand(cardId, file);
    addTriggeredBy(command, request);

    Attachment attachment = cardService.addAttachment(command);
    return AttachmentResult.build(attachment, fileUrlCreator);
  }


  @GetMapping("/api/cards/{cardId}/attachments")
  public ResponseEntity<ApiResult> getAttachments(@PathVariable long cardId) {
    System.out.println("GET: getAttachments() 호출");
    List<Attachment> attachments = cardService.getAttachments(new CardId(cardId));
    return AttachmentResults.build(attachments, fileUrlCreator);
  }

  @DeleteMapping("/api/cards/{cardId}/attachments")
  public ResponseEntity<ApiResult> deleteAttachments(@PathVariable long cardId) {
    System.out.println("GET: deleteAttachments() 호출");
    cardService.deleteAttachments(new CardId(cardId));
    return Result.ok();
  }

  @DeleteMapping("/api/cards/{cardId}")
  public ResponseEntity<ApiResult> deleteCard(@PathVariable long cardId) {
    System.out.println("DELETE: deleteCard() 호출");
    cardService.deleteCard(new CardId(cardId));
    return Result.ok();
  }
}
