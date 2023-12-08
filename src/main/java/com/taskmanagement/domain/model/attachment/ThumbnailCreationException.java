package com.taskmanagement.domain.model.attachment;

/**
 * 이미지의 경우 썸네일 생성 실패에 대한 예외 - 에러 메시지 발생
 */
public class ThumbnailCreationException extends RuntimeException{

  private static final long serialVersionUID = 7009707385413196703L;

  public ThumbnailCreationException(String message) {
    super(message);
  }

  public ThumbnailCreationException(String message, Throwable cause) {
    super(message, cause);
  }
}
