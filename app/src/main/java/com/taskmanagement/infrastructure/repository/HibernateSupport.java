package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.team.TeamId;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

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
