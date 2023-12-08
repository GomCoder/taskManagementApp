package com.taskmanagement.domain.model.attachment;

/**
 * 첨부파일 저장 실패 예외처리 - 에러 메시지 발생
 */
public class AttachmentCreationException extends RuntimeException{

  private static final long serialVersionUID = 870232032605462679L;

  public AttachmentCreationException(String message, Throwable cause) {
    super(message, cause);
  }
}
