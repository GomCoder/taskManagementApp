package com.taskmanagement.domain.model.user;

import com.taskmanagement.domain.common.security.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 사용자 등록 관리 클래스
 */
@Component
public class RegistrationManagement {

  private UserRepository repository;
  private PasswordEncryptor passwordEncryptor;

  /**
   * RegistrationManagement 생성자
   * @param repository
   * @param passwordEncryptor
   */
  public RegistrationManagement(UserRepository repository, PasswordEncryptor passwordEncryptor) {
    this.repository = repository;
    this.passwordEncryptor = passwordEncryptor;
  }

  /**
   * 사용자 등록 메서드
   * @param username
   * @param emailAddress
   * @param password
   * @return newUser
   * @throws RegistrationException
   */
  public User register(String username, String emailAddress, String password) throws RegistrationException {
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

    //새로운 유저 생성
    User newUser = User.create(username, emailAddress.toLowerCase(), encryptedPassword);
    repository.save(newUser);
    return newUser;
  }

}
