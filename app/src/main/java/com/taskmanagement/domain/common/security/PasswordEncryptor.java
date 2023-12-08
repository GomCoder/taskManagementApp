package com.taskmanagement.domain.common.security;

/**
 * 비밀번호 암호화
 */
public interface PasswordEncryptor {
  /**
   * 원시 암호화
   * @param rawPassword
   * @return
   */
  String encrypt(String rawPassword);
}
