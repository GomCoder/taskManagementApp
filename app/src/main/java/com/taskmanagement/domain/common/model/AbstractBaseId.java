package com.taskmanagement.domain.common.model;

public abstract class AbstractBaseId {
  private long id;
  public AbstractBaseId(long id) {
    this.id = id;
  }

  public long value() {
    return id;
  }

  public boolean isValid() {
    return id > 0;
  }

}
