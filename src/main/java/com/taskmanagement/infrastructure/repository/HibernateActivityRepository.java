package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.activity.Activity;
import com.taskmanagement.domain.model.activity.ActivityRepository;
import com.taskmanagement.domain.model.card.CardId;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 활동 이력 Hibernate 레포지토리
 */
@Repository
public class HibernateActivityRepository extends HibernateSupport<Activity> implements ActivityRepository {

  HibernateActivityRepository(EntityManager entityManager) {
    super(entityManager);
  }
  /**
   * 카드 활동 이력 조회
   */
  @Override
  public List<Activity> findCardActivities(CardId cardId) {
    String sql = "select a.* from activity a where a.card_id = :cardId order by id desc";
    NativeQuery<Activity> query = getSession().createNativeQuery(sql, Activity.class);
    query.setParameter("cardId", cardId.value());
    return query.list();
  }
}
