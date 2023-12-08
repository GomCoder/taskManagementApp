package com.taskmanagement.web.payload;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.Assert.assertEquals;


/**
 * 회원 가입시 유효성 검증에 대한 테스트
 */
public class RegistrationPayloadTest {
  private Validator validator;

  @Before
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  //유효성 검사: 공백 페이로드인 경우 실패해야 함
  @Test
  public void 유효성_검사_공백인_경우_실패해야_함() {
    RegistrationPayload payload = new RegistrationPayload();

    Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
    assertEquals(3, violations.size());
  }

  //유효성 검사: 잘못된 이메일로 실패해야 함
  @Test
  public void 유효성_검사_잘못된_이메일로_실패해야_함() {
    RegistrationPayload payload = new RegistrationPayload();

    payload.setEmailAddress("BadEmailAddress");
    payload.setUsername("MyUsername");
    payload.setPassword("MyPassword");

    Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
    assertEquals(1, violations.size());
  }

  //유효성 검사: 전자 메일 주소가 100보다 긴 페이로드인 경우 실패해야 함
  @Test
  public void 유효성_검사_이메일_주소가_100보다_긴_페이로드인_경우_실패해야_함() {
    int maxLocalParthLength = 64;
    String localPart = RandomStringUtils.random(maxLocalParthLength, true, true);
    int usedLength = maxLocalParthLength + "@".length() + ".com".length();
    String domain = RandomStringUtils.random(101 - usedLength, true, true);

    RegistrationPayload payload = new RegistrationPayload();
    payload.setEmailAddress(localPart + "@" + domain + ".com");
    payload.setUsername("MyUername");
    payload.setPassword("MyPassword");

    Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
    assertEquals(1, violations.size());
  }

  @Test
  public void 유효성_검사_사용자_이름이_2보다_짧은_경우_실패해야_함() {
    RegistrationPayload payload = new RegistrationPayload();
    String usernameTooShort = RandomStringUtils.random(1);

    payload.setUsername(usernameTooShort);
    payload.setEmailAddress("test@test.com");
    payload.setPassword("MyPassword");

    Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
    assertEquals(1, violations.size());
  }

  @Test
  public void 유효성_검사_사용자_이름이_50보다_긴_경우_실패해야_함() {
    RegistrationPayload payload = new RegistrationPayload();
    String usernameTooLong = RandomStringUtils.random(51);

    payload.setUsername(usernameTooLong);
    payload.setEmailAddress("test@test.com");
    payload.setPassword("MyPassword");

    Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
    assertEquals(1, violations.size());
  }

  @Test
  public void 유효성_검사_사용자_패스워드가_6인_경우_실패해야_함() {
    RegistrationPayload payload = new RegistrationPayload();
    String passwordTooShort = RandomStringUtils.random(5);

    payload.setUsername("MyUsername");
    payload.setEmailAddress("test@test.com");
    payload.setPassword(passwordTooShort);

    Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
    assertEquals(1, violations.size());
  }

  @Test
  public void 유효성_검사_사용자_패스워드가_30보다_긴_경우_실패해야_함() {
    RegistrationPayload payload = new RegistrationPayload();
    String passwordTooLong = RandomStringUtils.random(31);

    payload.setUsername("MyUsername");
    payload.setEmailAddress("test@test.com");
    payload.setPassword(passwordTooLong);

    Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
    assertEquals(1, violations.size());
  }
}
