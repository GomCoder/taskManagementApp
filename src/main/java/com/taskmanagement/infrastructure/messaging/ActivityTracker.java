package com.taskmanagement.infrastructure.messaging;

import com.taskmanagement.domain.application.ActivityService;
import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.model.activity.Activity;
import com.taskmanagement.domain.model.activity.DomainEventToActivityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 활동 이력 추적 - RabbitMQ 사용
 */
@Component
public class ActivityTracker {
  private final static Logger log = LoggerFactory.getLogger(ActivityTracker.class);

  private final ActivityService activityService;
  private final DomainEventToActivityConverter domainEventToActivityConverter;

  public ActivityTracker(ActivityService activityService, DomainEventToActivityConverter domainEventToActivityConverter) {
    this.activityService = activityService;
    this.domainEventToActivityConverter = domainEventToActivityConverter;
  }

  @RabbitListener(queues = "#{activityTrackingQueue.name}")
  public void receive(DomainEvent domainEvent) {
    log.debug("Receive domain event: " + domainEvent);

    Activity activity = domainEventToActivityConverter.toActivity(domainEvent);

    if (activity != null) {
      activityService.saveActivity(activity);
    }
  }
}
