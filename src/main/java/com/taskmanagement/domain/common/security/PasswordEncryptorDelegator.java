package com.taskmanagement.domain.common.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 비밀번호 암호화
 */
@Component
public class PasswordEncryptorDelegator implements PasswordEncryptor {

  private final PasswordEncoder passwordEncoder;

  public PasswordEncryptorDelegator(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
  @Override
  public String encrypt(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }
}
