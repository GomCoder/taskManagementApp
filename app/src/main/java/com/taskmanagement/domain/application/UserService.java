package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.RegisterCommand;
import com.taskmanagement.domain.model.user.RegistrationException;
import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserId;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  /**
   * 아이디로 사용자 조회하기
   * @param userId 사용자 아이디
   * @return 사용자 정보
   */
  User findById(UserId userId);
  /**
   * 새 사용자를 사용자 이름, 전자 메일 주소 및 암호로 등록합니다.
   * 성공: 아무것도 반환하지 않음
   * 실패: RegistrationException 반환함
   * @param command 등록 명령
   * @throws RegistrationException 등록시 예외처리 발생
   */
  void register(RegisterCommand command) throws RegistrationException;
}
