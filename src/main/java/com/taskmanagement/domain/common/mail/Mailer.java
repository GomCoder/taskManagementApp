package com.taskmanagement.domain.common.mail;

public interface Mailer {
  /**
   * 메시지 전송하기
   * @param message
   */
  void send(Message message);
}
