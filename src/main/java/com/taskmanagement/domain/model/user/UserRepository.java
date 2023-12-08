package com.taskmanagement.domain.model.user;

/**
 * 사용자 레포지토리 - 사용자 이름으로 사용자 조회, 이메일 주소로 사용자 조회, 
 *                    사용자 아이디로 사용자 조회, 사용자 저장
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
   * ID로 사용자 조회
   * @param userId
   * @return
   */
  User findById(UserId userId);

  /**
   * 새 사용자 또는 기존 사용자 저장
   *
   * @param user
   * @return
   */
  void save(User user);
}
