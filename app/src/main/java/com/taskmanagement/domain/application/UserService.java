package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.RegistrationCommand;
import com.taskmanagement.domain.model.user.RegistrationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  /**
   * 새 사용자를 사용자 이름, 전자 메일 주소 및 암호로 등록합니다.
   * @param command
   * @throws RegistrationException
   */
  void register(RegistrationCommand command) throws RegistrationException;
}
