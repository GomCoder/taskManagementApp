package com.taskmanagement.domain.common.mail;

public interface MailManager {
  /**
   * 수신인에게 메일 보내기
   * @param emailAddress
   * @param subject
   * @param template
   * @param variables
   */
  void send(String emailAddress, String subject, String template, MessageVariable... variables);
}
