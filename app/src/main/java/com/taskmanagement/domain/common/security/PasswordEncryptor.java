package com.taskmanagement.domain.common.security;

public interface PasswordEncryptor {
  /**
   * 원시 암호화
   * @param rawPassword
   * @return
   */
  String encrypt(String rawPassword);
}
