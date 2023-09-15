package com.taskmanagement.domain.common.mail;

import java.util.Objects;

public class SimpleMessage implements Message {
  private String to;
  private String subject;
  private String body;
  private String form;

  public SimpleMessage(String to, String subject, String body, String form) {
    this.to = to;
    this.subject = subject;
    this.body = body;
    this.form = form;
  }

  public String getTo() {
    return to;
  }

  public String getSubject() {
    return subject;
  }

  public String getBody() {
    return body;
  }

  public String getFrom() {
    return form;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SimpleMessage that = (SimpleMessage) o;
    return Objects.equals(to, that.to) &&
      Objects.equals(subject, that.subject) &&
      Objects.equals(body, that.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(to, subject, body);
  }

  @Override
  public String toString() {
    return "SimpleMessage{" +
      "to='" + to + '\'' +
      ", subject='" + subject + '\'' +
      ", body='" + body + '\'' +
      '}';
  }
}
