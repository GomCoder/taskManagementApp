package com.taskmanagement.web.apis.authenticate;

import com.taskmanagement.utils.JsonUtils;
import com.taskmanagement.web.results.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 사용자 인증 성공 핸들러
 */
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) throws IOException {
    response.setStatus(HttpStatus.OK.value());
    JsonUtils.write(response.getWriter(), ApiResult.message("authenticated"));
  }
}
