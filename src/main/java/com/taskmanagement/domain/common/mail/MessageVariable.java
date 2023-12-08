package com.taskmanagement.domain.common.mail;

import org.aspectj.bridge.Message;

import java.util.Objects;

/**
 * 메시지에 보낼 변수값 저장 후 인스턴스 생성
 */
public class MessageVariable {

  private final String key;
  private final Object value;

  private MessageVariable(String key, Object value) {
    this.key = key;
    this.value = value;
  }

  public static MessageVariable from(String key, Object value) {
   return new MessageVariable(key, value);
  }

  public String getKey() {
    return key;
  }

  public Object getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MessageVariable that = (MessageVariable) o;
    return Objects.equals(key, that.key) && Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString() {
    return "MessageVariable{" +
      "key='" + key + '\'' +
      ", value=" + value +
      '}';
  }
}
