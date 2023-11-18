package com.taskmanagement.domain.model.attachment;

public class AttachmentCreationException extends RuntimeException{

  private static final long serialVersionUID = 870232032605462679L;

  public AttachmentCreationException(String message, Throwable cause) {
    super(message, cause);
  }
}
