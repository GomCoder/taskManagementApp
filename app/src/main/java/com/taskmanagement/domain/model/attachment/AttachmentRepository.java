package com.taskmanagement.domain.model.attachment;

import com.taskmanagement.domain.model.card.CardId;

import java.util.List;

public interface AttachmentRepository {
  /**
   * 카드 이력 조회하기
   * @param cardId
   * @return
   */
  List<Attachment> findAttachments(CardId cardId);

  /**
   * 활동 이력 저장하기
   * @param attachment
   */
  void save(Attachment attachment);
}
