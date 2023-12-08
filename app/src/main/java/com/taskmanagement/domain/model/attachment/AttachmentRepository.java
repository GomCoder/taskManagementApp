package com.taskmanagement.domain.model.attachment;

import com.taskmanagement.domain.model.card.CardId;

import java.util.List;

/**
 * 첨부파일 레포지토리 - 아이디로 첨부파일 조회, 카드 아이디로 첨부파일 조회, 활동 이력에 저장, 
 *                     첨부 파일 삭제
 */
public interface AttachmentRepository {

  /**
   * 첨부파일을 Id로 조회하기
   * @param attachmentId
   * @return
   */
  Attachment findById(AttachmentId attachmentId);

  /**
   * 카드 아이디로 첨부파일 조회
   * @param cardId
   * @return
   */
  List<Attachment> findAttachments(CardId cardId);

  /**
   * 활동 이력에 저장하기
   * @param attachment
   */
  void save(Attachment attachment);

  /**
   * 첨부파일 삭제하기
   * @param attachmentId
   */
  void deleteAttachment(AttachmentId attachmentId);
}
