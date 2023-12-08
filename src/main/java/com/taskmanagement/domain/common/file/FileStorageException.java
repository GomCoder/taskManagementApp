package com.taskmanagement.domain.common.file;

/**
 * 파일 저장시 예외처리 - 에러 메시지 발생
 */
public class FileStorageException extends RuntimeException {
  public FileStorageException(String message) {
    super(message);
  }

  public FileStorageException(String message, Throwable cause) {
    super(message, cause);
  }
}
