package com.taskmanagement.utils;

import java.io.Serializable;
import java.util.Objects;

/**
 * 접속한 IP주소 
 */
public class IpAddress implements Serializable {
  private static final long serialVersionUID = 5581605778499278428L;
  private final String value;

  public IpAddress(String value) {
    this.value = value == null ? "" : value;
  }

  public String value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof IpAddress)) return false;
    IpAddress ipAddress = (IpAddress) o;
    return Objects.equals(value, ipAddress.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "IpAddress{" +
      "value='" + value + '\'' +
      '}';
  }
}
