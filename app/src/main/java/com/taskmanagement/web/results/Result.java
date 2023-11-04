package com.taskmanagement.web.results;
/**
 * 서버의 응답에 사용하는 클래스
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

public final class Result {
  private Result() {}

  /**
   * 생성에 대한 상태 응답 201
   * @return ResponseEntity.status(201).build()
   */
  public static ResponseEntity<ApiResult> created() {
    return ResponseEntity.status(201).build();
  }

  /**
   * ok 상태일 때의 응답
   * @return ResponseEntity.ok().build()
   */
  public static ResponseEntity<ApiResult> ok() {
    return ResponseEntity.ok().build();
  }

  /**
   * 메시지가 비었을 때의 응답
   * @param message
   * @return ok(ApiResult.message(message))
   */
  public static ResponseEntity<ApiResult> ok(String message) {
    Assert.hasText(message, "Parameter `message` must not be blank");
    return ok(ApiResult.message(message));
  }

  /**
   * 데이터가 비었을 때의 응답
   * @param payload
   * @return ResponseEntity.ok(payload)
   */
  public static ResponseEntity<ApiResult> ok(ApiResult payload) {
    Assert.notNull(payload, "Parameter `payload` must not be null");
    return ResponseEntity.ok(payload);
  }

  /**
   * 실패에 대한 응답
   *
   * @param message
   * @return ResponseEntity.badRequest().body(ApiResult.message(message)
   */
  public static ResponseEntity<ApiResult> failure(String message) {
    return ResponseEntity.badRequest().body(ApiResult.message(message));
  }

  /**
   * 서버 에러에 대한 응답
   * @param message
   * @param errorReferenceCode
   * @return ResponseEntity.status(500).body(ApiResult.error(message, errorReferenceCode)
   */
  public static ResponseEntity<ApiResult> serverError(String message, String errorReferenceCode) {
    return ResponseEntity.status(500).body(ApiResult.error(message, errorReferenceCode));
  }

  /**
   * 해당 리소스를 찾지 못했을 때의 응답
   * @return ResponseEntity.notFound().build()
   */
  public static ResponseEntity<ApiResult> notFound() {
    return ResponseEntity.notFound().build();
  }

  /**
   * 인증되지 않은 접근일 때의 응답
   * @return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
   */
  public static ResponseEntity<ApiResult> unauthenticated() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  /**
   * 금지된 접근에 대한 응답
   * @return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
   */
  public static ResponseEntity<ApiResult> forbidden() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }
}
