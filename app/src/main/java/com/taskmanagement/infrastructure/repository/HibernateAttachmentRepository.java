package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.attachment.Attachment;
import com.taskmanagement.domain.model.attachment.AttachmentId;
import com.taskmanagement.domain.model.attachment.AttachmentRepository;
import com.taskmanagement.domain.model.card.CardId;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateAttachmentRepository extends HibernateSupport<Attachment> implements AttachmentRepository {
  HibernateAttachmentRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public Attachment findById(AttachmentId attachmentId) {
    return getSession().find(Attachment.class, attachmentId.value());
  }

  @Override
  public List<Attachment> findAttachments(CardId cardId) {
    System.out.println("HibernateAttachmentRepository.findAttachments(): " +  cardId.value());
    String sql = "select a.* from attachment a where a.card_id = :cardId order by id desc";
    NativeQuery<Attachment> query = getSession().createNativeQuery(sql, Attachment.class);
    query.setParameter("cardId", cardId.value());
    return query.list();
  }

  @Override
  public void deleteAttachment(AttachmentId attachmentId) {
    String sql = "delete from attachment where id = :attachmentId";
    NativeQuery<Attachment> query = getSession().createNativeQuery(sql, Attachment.class);
    query.setParameter("attachmentId", attachmentId.value());
    query.executeUpdate();
  }

}
