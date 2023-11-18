package com.taskmanagement.domain.common.model;

import java.io.Serializable;

public abstract class AbstractBaseEntity implements Serializable {
  private static final long serialVersionUID = -2213306721777733218L;

  public abstract boolean equals(Object obj);
  public abstract int hashCode();
  public abstract String toString();
}
