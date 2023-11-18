package com.taskmanagement.domain.model.activity;

import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.model.attachment.event.CardAttachmentAddedEvent;
import com.taskmanagement.domain.model.board.events.BoardCreatedEvent;
import com.taskmanagement.domain.model.board.events.BoardMemberAddedEvent;
import com.taskmanagement.domain.model.card.events.CardAddedEvent;
import com.taskmanagement.domain.model.card.events.CardDescriptionChangedEvent;
import com.taskmanagement.domain.model.card.events.CardTitleChangedEvent;
import com.taskmanagement.domain.model.cardlist.events.CardListAddedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DomainEventToActivityConverter {
  private static final Logger log = LoggerFactory.getLogger(DomainEventToActivityConverter.class);

  public Activity toActivity(DomainEvent event) {
    if (event instanceof BoardCreatedEvent) {
      return BoardActivities.from((BoardCreatedEvent) event);
    } else if (event instanceof BoardMemberAddedEvent) {
      return BoardActivities.from((BoardMemberAddedEvent) event);
    } else if (event instanceof CardAttachmentAddedEvent) {
      return CardActivities.from((CardAttachmentAddedEvent) event);
    } else if (event instanceof CardAddedEvent) {
      return CardActivities.from((CardAddedEvent) event);
    } else if (event instanceof CardDescriptionChangedEvent) {
      return CardActivities.from((CardDescriptionChangedEvent) event);
    } else if (event instanceof CardTitleChangedEvent) {
      return CardActivities.from((CardTitleChangedEvent) event);
    } else if (event instanceof CardListAddedEvent) {
      return CardListActivities.from((CardListAddedEvent) event);
    }

    log.debug("No activity converted from " + event);
    return null;
  }
}
