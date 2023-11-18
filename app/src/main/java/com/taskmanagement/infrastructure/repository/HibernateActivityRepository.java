package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.activity.Activity;
import com.taskmanagement.domain.model.activity.ActivityRepository;
import com.taskmanagement.domain.model.card.CardId;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateActivityRepository extends HibernateSupport<Activity> implements ActivityRepository {

  HibernateActivityRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Activity> findCardActivities(CardId cardId) {
    String sql = "SELECT a.* FROM activity a WHERE a.card_id = :cardId ORDER BY id DESC";
    NativeQuery<Activity> query = getSession().createNativeQuery(sql, Activity.class);
    query.setParameter("cardId", cardId.value());
    return query.list();
  }
}
