package com.taskmanagement.domain.model.attachment;

import com.taskmanagement.domain.model.card.CardId;

import java.util.List;

public interface AttachmentRepository {

  /**
   * 첨부파일을 Id로 조회하기
   * @param attachmentId
   * @return
   */
  Attachment findById(AttachmentId attachmentId);

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

  /**
   * 첨부파일 삭제하기
   * @param attachmentId
   */
  void deleteAttachment(AttachmentId attachmentId);
}
