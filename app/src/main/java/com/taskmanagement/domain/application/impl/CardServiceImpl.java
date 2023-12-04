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

  @Override
  public List<Card> findByBoardId(BoardId boardId) {
    return cardRepository.findByBoardId(boardId);
  }

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

  @Override
  public void changePositions(ChangeCardPositionsCommand command) {
    cardRepository.changePositions(command.getCardPositions());
  }

  @Override
  public Card findById(CardId cardId) {
    return cardRepository.findById(cardId);
  }

  @Override
  public List<Activity> findCardActivities(CardId cardId) {
    return activityRepository.findCardActivities(cardId);
  }

  @Override
  public List<Attachment> getAttachments(CardId cardId) {
    return attachmentRepository.findAttachments(cardId);
  }

  @Override
  public void changeCardTitle(ChangeCardTitleCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Card card = findCard(command.getCardId());
    String oldTitle = card.getTitle();
    card.changeTitle(command.getTitle());
    cardRepository.save(card);
    domainEventPublisher.publish(new CardTitleChangedEvent(card, oldTitle, command));
  }

  @Override
  public void changeCardDescription(ChangeCardDescriptionCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Card card = findCard(command.getCardId());
    String oldDescription = card.getDescription();
    card.changeDescription(command.getDescription());
    cardRepository.save(card);
    domainEventPublisher.publish(new CardDescriptionChangedEvent(card, oldDescription, command));
  }

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
    System.out.println("attachment.getFilePath(): " + attachment.getFilePath());
    return attachment;
  }

  private Card findCard(CardId cardId) {
    Card card = cardRepository.findById(cardId);
    Assert.notNull(card, "Card of id " + card + " must exist");
    return card;
  }

  public void deleteCard(DeleteCardCommand command){
    cardRepository.deleteCard(command.getCardId());
  }

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
