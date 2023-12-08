package com.taskmanagement.domain.model.user;

/**
 * 이미 존재하는 이메일 주소에 대한 예외처리
 */
public class EmailAddressExistsException extends RegistrationException {
  private static final long serialVersionUID = 5564158789815708486L;
}
