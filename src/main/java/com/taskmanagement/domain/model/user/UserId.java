package com.taskmanagement.domain.model.user;

import com.taskmanagement.domain.common.model.AbstractBaseId;

/**
 * 사용자(엔터티) 아이디
 */
public class UserId extends AbstractBaseId {
  private static final long serialVersionUID = 7387759337201204993L;

  public UserId(long id) {
    super(id);
  }

  public String toString() {
    return String.valueOf(value());
  }
}
