package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.attachment.Attachment;
import com.taskmanagement.domain.model.attachment.AttachmentRepository;
import com.taskmanagement.domain.model.card.CardId;
import org.hibernate.query.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateAttachmentRepository extends HibernateSupport<Attachment> implements AttachmentRepository {
  HibernateAttachmentRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Attachment> findAttachments(CardId cardId) {
    System.out.println("HibernateAttachmentRepository.findAttachments(): " +  cardId.value());
    String sql = "select a.* from attachment a where a.card_id = :cardId order by id desc";
    NativeQuery<Attachment> query = getSession().createNativeQuery(sql, Attachment.class);
    query.setParameter("cardId", cardId.value());
    return query.list();
  }
}
