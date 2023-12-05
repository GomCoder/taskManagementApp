package com.taskmanagement.web.apis;

import com.taskmanagement.web.results.ApiResult;
import com.taskmanagement.web.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

/**
 * API 예외 발생시 핸들러
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

  @ExceptionHandler({Exception.class})
  protected ResponseEntity<ApiResult> handle(RuntimeException ex) {
    String errorReferenceCode = UUID.randomUUID().toString();
    log.error("Unhandled exception error [code=" + errorReferenceCode + "]" + ex);
    return Result.serverError("Sorry, there is an error on the server side.", errorReferenceCode);
  }

  @ExceptionHandler({MaxUploadSizeExceededException.class})
  protected ResponseEntity<ApiResult> handle(MaxUploadSizeExceededException ex) {
    return Result.failure("File exceeded maximum size limit");
  }
}
