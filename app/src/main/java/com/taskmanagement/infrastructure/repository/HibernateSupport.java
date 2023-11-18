package com.taskmanagement.infrastructure.repository;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public abstract class HibernateSupport<T> {
  private final EntityManager entityManager;
  @Autowired
  HibernateSupport(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
  Session getSession() {
    return entityManager.unwrap(Session.class);
  }
  public void save(T object) {
    entityManager.persist(object);
    entityManager.flush();
  }
}
