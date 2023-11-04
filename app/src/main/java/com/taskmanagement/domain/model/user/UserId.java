package com.taskmanagement.domain.model.user;

import com.taskmanagement.domain.common.model.AbstractBaseId;

public class UserId extends AbstractBaseId {
  public UserId(long id) {
    super(id);
  }

  public String toString() {
    return String.valueOf(value());
  }
}
