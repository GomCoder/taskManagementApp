package com.taskmanagement.domain.common.event;

import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.utils.IpAddress;

import java.io.Serializable;
import java.util.Date;

/**
 * 도메인 이벤트: 누가 몇시에 무엇을 했는 지에 대한 클래스
 */
public abstract class DomainEvent implements Serializable {
  private static final long serialVersionUID = -6349189846826590067L;
  private final UserId userId;
  private final IpAddress ipAddress;
  private final Date occurredAt;

  public DomainEvent() {
    this.userId = getUserId();
    this.ipAddress = getIpAddress();
    this.occurredAt = getOccurredAt();
  }

  public DomainEvent(TriggeredBy triggeredBy) {
    this.userId = triggeredBy.getUserId();
    this.ipAddress = triggeredBy.getIpAddress();
    this.occurredAt = new Date();
  }

  public DomainEvent(UserId userId, TriggeredFrom triggeredFrom) {
    this.userId = userId;
    this.ipAddress = triggeredFrom.getIpAddress();
    this.occurredAt = new Date();
  }

    public UserId getUserId() {
    return userId;
  }

  public IpAddress getIpAddress() {
    return ipAddress;
  }

  public Date getOccurredAt() {
    return occurredAt;
  }
}
