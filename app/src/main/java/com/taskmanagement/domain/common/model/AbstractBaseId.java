package com.taskmanagement.domain.common.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 추상 기본 아이디
 */
public abstract class AbstractBaseId implements Serializable {
  private static final long serialVersionUID = 4565262348938261741L;
  private final long id;
  public AbstractBaseId(long id) {
    this.id = id;
  }

  public long value() {
    return id;
  }

  public boolean isValid() {
    return id > 0;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(!(o instanceof AbstractBaseId)) return false;
    AbstractBaseId that = (AbstractBaseId) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
