package com.taskmanagement.domain.model.attachment;

public class ThumbnailCreationException extends RuntimeException{

  private static final long serialVersionUID = 7009707385413196703L;

  public ThumbnailCreationException(String message) {
    super(message);
  }

  public ThumbnailCreationException(String message, Throwable cause) {
    super(message, cause);
  }
}
