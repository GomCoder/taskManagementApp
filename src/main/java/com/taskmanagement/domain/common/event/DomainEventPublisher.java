package com.taskmanagement.domain.common.event;

public interface DomainEventPublisher {
  /**
   * 도메인 이벤트 게시 메서드
   * @param event
   */
  void publish(DomainEvent event);
}
