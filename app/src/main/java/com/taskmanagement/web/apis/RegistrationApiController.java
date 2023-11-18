package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.UserService;
import com.taskmanagement.domain.application.commands.RegisterCommand;
import com.taskmanagement.domain.model.user.EmailAddressExistsException;
import com.taskmanagement.domain.model.user.RegistrationException;
import com.taskmanagement.domain.model.user.UsernameExistsException;
import com.taskmanagement.web.payload.RegistrationPayload;
import com.taskmanagement.web.results.ApiResult;
import com.taskmanagement.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 회원가입 요청을 처리하기 위한 API Handler
 * - Spring MVC는 @Valid 어노테이션이 존재하면 register() 메서드에 데이터를 전달하기 전에
 *   RegistrationPayload의 데이터 검증을 수행하여 데이터가 유효한지 확인함
 * - Handler에서는 RegistrationPayload 인스턴스를 RegistrationCommand 클래스로 변환한 뒤,
 *   RegistrationCommand 클래스의 인스턴스와 함께 Service API를 호출함
 * - 성공: Result.created()를 호출하여 HTTP 201 응답을 반환
 * - 실패: RegistrationException 예외 처리, 관련 메시지와 HTTP 400 응답을 반환
 */
@Controller
public class RegistrationApiController extends AbstractBaseController {
  //사용자를 등록할 때 활용할 register API 제공
  private final UserService service;

  //생성자
  public RegistrationApiController(UserService service) {
    this.service = service;
  }

  /**
   * RegistrationPayload의 인스턴스: Spring MVC가 Request Body를 활용해서 생성하며 자동으로 데이터를 검증함
   * @param payload
   * @return ResponseEntity의 인스턴스 반환 -> Spring MVC가 HTTP 응답을 생성하는 데 사용함
   */
  @PostMapping("/api/registrations")
  public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload,
                                            HttpServletRequest request) {
    try {
      RegisterCommand command = payload.toCommand();
      addTriggeredBy(command, request);
      service.register(command);
      return Result.created();
    } catch(RegistrationException e) {
      String errorMessage = "Registration failed";

      if(e instanceof UsernameExistsException) {
        errorMessage = "Username already exists";
      } else if (e instanceof EmailAddressExistsException) {
        errorMessage = "Email address already exists";
      }
      return Result.failure(errorMessage);
    }
  }
}
