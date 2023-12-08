package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.domain.model.user.UserRepository;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * 사용자 Hibernate 레포지토리
 */
@Repository
public class HibernateUserRepository extends HibernateSupport<User> implements UserRepository {

  @Autowired
  public HibernateUserRepository(EntityManager entityManager) {
    super(entityManager);
  }

  /**
   * 사용자 이름으로 사용자 조회
   */
  @Override
  public User findByUsername(String username) {
    Query<User> query = getSession().createQuery("from User where username = :username", User.class);
    query.setParameter("username", username);
    return query.uniqueResult();
  }

  /**
   * 이메일 주소로 사용자 조회
   */
  @Override
  public User findByEmailAddress(String emailAddress) {
    Query<User> query = getSession().createQuery("from User where emailAddress = :emailAddress", User.class);
    query.setParameter("emailAddress", emailAddress);
    return query.uniqueResult();
  }

  /**
   * 사용자 아이디로 사용자 조회
   */
  @Override
  public User findById(UserId userId) {
    Query<User> query = getSession().createQuery("from User where id = :id", User.class);
    query.setParameter("id", userId.value());
    return query.uniqueResult();
  }
}
