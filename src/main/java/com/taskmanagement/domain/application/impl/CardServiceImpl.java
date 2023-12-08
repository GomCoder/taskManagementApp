package com.taskmanagement.domain.application.impl;

import com.taskmanagement.domain.application.CardService;
import com.taskmanagement.domain.application.commands.*;
import com.taskmanagement.domain.common.event.DomainEventPublisher;
import com.taskmanagement.domain.model.activity.Activity;
import com.taskmanagement.domain.model.activity.ActivityRepository;
import com.taskmanagement.domain.model.activity.CardActivities;
import com.taskmanagement.domain.model.attachment.Attachment;
import com.taskmanagement.domain.model.attachment.AttachmentId;
import com.taskmanagement.domain.model.attachment.AttachmentManagement;
import com.taskmanagement.domain.model.attachment.AttachmentRepository;
import com.taskmanagement.domain.model.attachment.event.CardAttachmentAddedEvent;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.domain.model.card.CardId;
import com.taskmanagement.domain.model.card.CardRepository;
import com.taskmanagement.domain.model.card.events.CardAddedEvent;
import com.taskmanagement.domain.model.card.events.CardDescriptionChangedEvent;
import com.taskmanagement.domain.model.card.events.CardTitleChangedEvent;
import com.taskmanagement.domain.model.cardlist.CardList;
import com.taskmanagement.domain.model.cardlist.CardListRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 카드 서비스 구현
 */
@Service
@Transactional
public class CardServiceImpl implements CardService {
  private final CardRepository cardRepository;
  private final CardListRepository cardListRepository;
  private final ActivityRepository activityRepository;
  private final AttachmentRepository attachmentRepository;
  private final AttachmentManagement attachmentManagement;
  private final DomainEventPublisher domainEventPublisher;

  public CardServiceImpl(CardRepository cardRepository,
                         CardListRepository cardListRepository,
                         ActivityRepository activityRepository,
                         AttachmentRepository attachmentRepository,
                         AttachmentManagement attachmentManagement,
                         DomainEventPublisher domainEventPublisher) {
    this.cardRepository = cardRepository;
    this.cardListRepository = cardListRepository;
    this.attachmentRepository = attachmentRepository;
    this.attachmentManagement = attachmentManagement;
    this.activityRepository = activityRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  /**
   * 보드 아이디로 카드 조회하기
   */
  @Override
  public List<Card> findByBoardId(BoardId boardId) {
    return cardRepository.findByBoardId(boardId);
  }

  /* 
   * 카드 추가 
   */
  @Override
  public Card addCard(AddCardCommand command) {
    CardList cardList = cardListRepository.findById(command.getCardListId());
    Assert.notNull(cardList, "Card list must noe be null");

    Card card = Card.create(cardList, command.getUserId(), command.getTitle(), command.getPosition());

    cardRepository.save(card);
    domainEventPublisher.publish(new CardAddedEvent(card, command));
    System.out.println(card.getCreatedDate());
    return card;
  }

  /**
   * 카드 위치 변경
   */
  @Override
  public void changePositions(ChangeCardPositionsCommand command) {
    cardRepository.changePositions(command.getCardPositions());
  }
  
  /**
   * 카드 아이디로 카드 조회
   */
  @Override
  public Card findById(CardId cardId) {
    return cardRepository.findById(cardId);
  }

  /**
   * 카드 아이디로 카드 활동 이력 조회
   */
  @Override
  public List<Activity> findCardActivities(CardId cardId) {
    return activityRepository.findCardActivities(cardId);
  }

  /**
   * 카드 아이디로 첨부파일 조회
   */
  @Override
  public List<Attachment> getAttachments(CardId cardId) {
    return attachmentRepository.findAttachments(cardId);
  }

  /**
   * 카드 제목 변경
   */
  @Override
  public void changeCardTitle(ChangeCardTitleCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Card card = findCard(command.getCardId());
    String oldTitle = card.getTitle();
    card.changeTitle(command.getTitle());
    cardRepository.save(card);
    domainEventPublisher.publish(new CardTitleChangedEvent(card, oldTitle, command));
  }

  /**
   * 카드 설명 변경
   */
  @Override
  public void changeCardDescription(ChangeCardDescriptionCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Card card = findCard(command.getCardId());
    String oldDescription = card.getDescription();
    card.changeDescription(command.getDescription());
    cardRepository.save(card);
    domainEventPublisher.publish(new CardDescriptionChangedEvent(card, oldDescription, command));
  }

  /**
   * 카드에 댓글 추가
   */
  @Override
  public Activity addComment(AddCardCommentCommand command) {
    Assert.notNull(command, "Parameter `command`must not be null");

    Card card = findCard(command.getCardId());

    Activity cardActivity = CardActivities.from(
      card, command.getUserId(), command.getComment(), command.getIpAddress()
    );

    activityRepository.save(cardActivity);
    return cardActivity;
  }

  /**
   * 카드에 첨부파일 추가
   */
  @Override
  public Attachment addAttachment(AddCardAttachmentCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");
    Card card = findCard(command.getCardId());
    Attachment attachment = attachmentManagement.save(command.getCardId(), command.getFile(), command.getUserId());

    if (!card.hasCoverImage() && attachment.isThumbnailCreated()) {
      card.addCoverImage(attachment.getFilePath());
      cardRepository.save(card);
    }

    domainEventPublisher.publish(new CardAttachmentAddedEvent(card, attachment, command));
    return attachment;
  }

  /**
   * 카드 아이디로 카드 정보 조회
   */
  private Card findCard(CardId cardId) {
    Card card = cardRepository.findById(cardId);
    Assert.notNull(card, "Card of id " + card + " must exist");
    return card;
  }

  /**
   * 카드 삭제
   */
  public void deleteCard(DeleteCardCommand command){
    cardRepository.deleteCard(command.getCardId());
  }

  /**
   * 카드에 있는 첨부파일 삭제
   */
  @Override
  public void removeAttachment(CardId cardId, AttachmentId attachmentId) {
    Assert.notNull(cardId, "Parameter 'cardId' must not be null" );
    Assert.notNull(attachmentId, "Parameter 'attachmentId' must not be null" );

    Card card = findCard(cardId);
    Attachment attachment = attachmentRepository.findById(attachmentId);

    if (attachment != null) {
      attachmentRepository.deleteAttachment(attachmentId);

      if(card.hasCoverImage() && card.getCoverImage().equals(attachment.getFilePath())) {
        card.removeCoverImage();
        cardRepository.save(card);
      }
    }
  }
}
