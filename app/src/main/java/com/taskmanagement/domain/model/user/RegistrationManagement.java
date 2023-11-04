package com.taskmanagement.domain.model.user;

import com.taskmanagement.domain.common.security.PasswordEncryptor;
import org.springframework.stereotype.Component;

/**
 * 사용자 등록 관리 클래스
 */
@Component
public class RegistrationManagement {

  private final UserRepository repository;
  private final PasswordEncryptor passwordEncryptor;

  /**
   * RegistrationManagement 생성자
   * @param repository: UserRepository
   * @param passwordEncryptor: PasswordEncryptor
   */
  public RegistrationManagement(UserRepository repository, PasswordEncryptor passwordEncryptor) {
    this.repository = repository;
    this.passwordEncryptor = passwordEncryptor;
  }

  /**
   * 사용자 등록 메서드
   * @param username: 사용자 이름
   * @param emailAddress: 이메일 주소
   * @param password: 비밀번호
   * @return newUser
   * @throws RegistrationException: 회원 가입 예외처리
   */
  public User register(String username, String emailAddress, String firstName, String lastName, String password) throws RegistrationException {
    //이미 존재 하는 사용자(username) -> UsernameExistsException()
    User existingUser = repository.findByUsername(username);

    if(existingUser != null) {
      throw new UsernameExistsException();
    }

    //이미 존재하는 사용자(emailAddress) -> EmailAddressExistsException();
    existingUser = repository.findByEmailAddress(emailAddress.toLowerCase());

    if(existingUser != null) {
      throw new EmailAddressExistsException();
    }

    //비밀번호 암호화
    String encryptedPassword = passwordEncryptor.encrypt(password);

    //새로운 유저 생성하고 저장하기
    User newUser = User.create(username, emailAddress.toLowerCase(), firstName, lastName, encryptedPassword);
    repository.save(newUser);
    return newUser;
  }
}
