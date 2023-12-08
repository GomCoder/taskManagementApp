package com.taskmanagement.domain.model.user;

import java.io.Serializable;

/**
 * 존재하지 않는 사용자에 대한 예외처리
 */
public class UserNotFoundException extends Exception implements Serializable {
  private static final long serialVersionUID = -3874606738539703598L;
}
