package com.taskmanagement.web.results;
/**
 * API의 결과를 생성하는 클래스
 */

import org.springframework.util.Assert;

import java.util.HashMap;

public class ApiResult extends HashMap<String, Object> {
  private static final long serialVersionUID = 877825499039674411L;
  private static final String MESSAGE_KEY = "message";
  private static final String ERROR_CODE_KEY = "errorReferenceCode";

  /**
   * 빈 칸일 때 새로운 ApiResult 객체를 생성함
   * @return
   */
  public static ApiResult blank() {
    return new ApiResult();
  }

  /**
   * 빈 칸일 때, 메세지 출력
   *
   * @param message
   * @return
   */
  public static ApiResult message(String message) {
    Assert.hasText(message, "Parameter `message` must not be blank");

    ApiResult apiResult = new ApiResult();
    apiResult.put("message", message);
    return apiResult;
  }

  /**
   * 에러 발생시 메세지 출력
   * @param message
   * @param errorReferenceCode
   * @return
   */
  public static ApiResult error(String message, String errorReferenceCode) {
    Assert.hasText(message, "Parameter `message` must not be blank");
    Assert.hasText(errorReferenceCode, "Parameter `errorReferenceCode` must not be blank");

    ApiResult apiResult = new ApiResult();
    apiResult.put(MESSAGE_KEY, message);
    apiResult.put(ERROR_CODE_KEY, errorReferenceCode);
    return apiResult;
  }

  /**
   * 입력 값에 대한 데이터 추가
   * @param key
   * @param value
   * @return
   */
  public ApiResult add(String key, Object value) {
    Assert.hasText(key, "Parameter `key` must not be blank");
    Assert.notNull(value, "Parameter `value` must not be nul");

    this.put(key, value);
    return this;
  }
}
