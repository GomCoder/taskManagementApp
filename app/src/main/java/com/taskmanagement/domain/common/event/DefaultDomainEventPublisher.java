package com.taskmanagement.domain.common.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 실제 이벤트의 발행자인 ApplicationEventPublisher 인스턴스를 주입한 다음,
 * 해당 인스턴스에 이벤트를 발행하는 작업을 위임함
 * - 여러 모델에서 공유하여 사용함
 */
@Component
public class DefaultDomainEventPublisher implements DomainEventPublisher {
  private final ApplicationEventPublisher actualPublisher;

  public DefaultDomainEventPublisher(ApplicationEventPublisher actualPublisher) {
    this.actualPublisher = actualPublisher;
  }

  @Override
  public void publish(DomainEvent event) {
    actualPublisher.publishEvent(event);
  }
}
