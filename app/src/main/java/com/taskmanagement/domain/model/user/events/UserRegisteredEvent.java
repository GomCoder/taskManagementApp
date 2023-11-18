package com.taskmanagement.domain.model.user.events;

import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.common.event.TriggeredFrom;
import com.taskmanagement.domain.model.user.User;

import java.io.Serializable;

public class UserRegisteredEvent extends DomainEvent implements Serializable {
  private static final long serialVersionUID = 5127684683045027895L;

  public UserRegisteredEvent(User user, TriggeredFrom triggeredFrom) {
    super(user.getId(), triggeredFrom);
  }

  public String toString() {
    return "UserRegisteredEvent{" +
      "userId='" + getUserId() + '\'' +
      '}';
  }
}
