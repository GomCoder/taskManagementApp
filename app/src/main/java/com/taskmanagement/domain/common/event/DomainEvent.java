package com.taskmanagement.domain.common.event;
import org.springframework.context.ApplicationEvent;
/**
 * 도메인 이벤트 추상화 메서드
 */
public abstract class DomainEvent extends ApplicationEvent{

  public DomainEvent(Object source) {
    super(source);
  }

  /**
   * 이 이벤트가 발생한 타임스템프 가져오기
   * @return
   */
  public long occurredAt() {
    //기본 구현의 타임스탬프 반환
    return getTimestamp();
  }
}
