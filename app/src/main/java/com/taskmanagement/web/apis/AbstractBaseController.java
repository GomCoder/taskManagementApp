package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.commands.AnonymousCommand;
import com.taskmanagement.domain.application.commands.UserCommand;
import com.taskmanagement.domain.model.user.SimpleUser;
import com.taskmanagement.utils.RequestUtils;
import io.jsonwebtoken.lang.Assert;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.http.HttpServletRequest;

/**
 * 추상 기본 컨트롤러
 */
public abstract class AbstractBaseController {
  void addTriggeredBy(UserCommand command, HttpServletRequest request) {
    Assert.notNull(request.getUserPrincipal(), "User principal must be present in the request");
    UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
    SimpleUser currentUser = (SimpleUser) userPrincipal.getPrincipal();
    command.triggeredBy(currentUser.getUserId(), RequestUtils.getIpAddress(request));
    System.out.println("addTriggeredBy(UserCommand command, HttpServletRequest request) 호출");
  }

  void addTriggeredBy(AnonymousCommand command, HttpServletRequest request) {
    command.triggeredBy(RequestUtils.getIpAddress(request));
    System.out.println("addTriggeredBy(AnonymousCommand command, HttpServletRequest request) 호출");
  }
}
