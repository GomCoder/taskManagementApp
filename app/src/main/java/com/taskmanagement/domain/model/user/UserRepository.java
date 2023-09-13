package com.taskmanagement.domain.model.user;

/**
 * 사용자 레포지토리 인터페이스
 */
public interface UserRepository {

  /**
   * 사용자 이름으로 사용자 조회
   * @param username
   * @return
   */
  User findByUsername(String username);

  /**
   * 이메일 주소로 사용자 조회
   * @param emailAddress
   * @return
   */
  User findByEmailAddress(String emailAddress);

  /**
   * 새 사용자 또는 기존 사용자 저장
   *
   * @param user
   * @return
   */
  void save(User user);
}
