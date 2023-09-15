package com.taskmanagement.domain.common.mail;

public interface Message {
  /**
   * 메시지 수신자 가져오기
   * @return 수신자의 이메일 주소
   */
  String getTo();

  /**
   * 메시지 제목 가져오기
   * @return 메시지 제목
   */
  String getSubject();

  /**
   * 메시지 본문 가져오기
   * @return 메시지 본문
   */
  String getBody();

  /**
   * 발신자의 메시지를 가져옴
   * @return 발신자
   */
  String getFrom();
}
