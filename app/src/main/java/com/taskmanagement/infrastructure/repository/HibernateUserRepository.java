package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.domain.model.user.UserRepository;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class HibernateUserRepository extends HibernateSupport<User> implements UserRepository {

  @Autowired
  public HibernateUserRepository(EntityManager entityManager) {
    super(entityManager);
    //this.entityManager = entityManager;
  }

  @Override
  public User findByUsername(String username) {
    Query<User> query = getSession().createQuery("from User where username = :username", User.class);
    query.setParameter("username", username);
    return query.uniqueResult();
  }

  @Override
  public User findByEmailAddress(String emailAddress) {
    Query<User> query = getSession().createQuery("from User where emailAddress = :emailAddress", User.class);
    query.setParameter("emailAddress", emailAddress);
    return query.uniqueResult();
  }

  @Override
  public User findById(UserId userId) {
    Query<User> query = getSession().createQuery("FROM User WHERE id = :id", User.class);
    query.setParameter("id", userId.value());
    return query.uniqueResult();
  }

//  @Override
//  public void save(User user) {
//    entityManager.persist(user);
//    entityManager.flush();
//  }
}
