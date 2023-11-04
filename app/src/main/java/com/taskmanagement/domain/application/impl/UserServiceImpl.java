package com.taskmanagement.domain.application.impl;

import com.taskmanagement.domain.application.UserService;
import com.taskmanagement.domain.application.commands.RegistrationCommand;
import com.taskmanagement.domain.common.mail.MessageVariable;
import com.taskmanagement.domain.model.user.*;
import com.taskmanagement.domain.common.event.DomainEventPublisher;
import com.taskmanagement.domain.common.mail.MailManager;
import com.taskmanagement.domain.model.user.events.UserRegisteredEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final RegistrationManagement registrationManagement;
  private final DomainEventPublisher domainEventPublisher;
  private final MailManager mailManager;

  private final UserRepository userRepository;

  public UserServiceImpl(RegistrationManagement registrationManagement, DomainEventPublisher domainEventPublisher, MailManager mailManager, UserRepository userRepository) {
    this.registrationManagement = registrationManagement;
    this.domainEventPublisher = domainEventPublisher;
    this.mailManager = mailManager;
    this.userRepository = userRepository;
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (StringUtils.isEmpty(username)) {
      throw new UsernameNotFoundException("No user found");
    }

    User user;

    if(username.contains("@")) {
      user = userRepository.findByEmailAddress(username);
    } else {
      user = userRepository.findByUsername(username);
    }

    if (user == null) {
      throw new UsernameNotFoundException("No user found by `" + username + "`");
    }
    return new SimpleUser(user);
  }

  /**
   * 새 사용자를 사용자 이름, 전자 메일 주소 및 암호로 등록합니다.
   * 성공: 아무것도 반환하지 않음
   * 실패: RegistrationException 반환함
   * @param command
   * @throws RegistrationException
   */
  @Override
  public void register(RegistrationCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
    User newUser = registrationManagement.register(
      command.getUsername(),
      command.getEmailAddress(),
      command.getFirstName(),
      command.getLastName(),
      command.getPassword());

    sendWelcomeMessage(newUser);
    domainEventPublisher.publish(new UserRegisteredEvent(this, newUser));
  }
  /**
   * 아이디로 사용자 조회하기
   * @param userId
   * @return userRepository.findById(userId)
   */
  @Override
  public User findById(UserId userId) {
    return userRepository.findById(userId);
  }

  /**
   * 새로운 사용자에게 보낼 Email 메시지 보내기
   * @param user: 새로운 사용자
   */
  private void sendWelcomeMessage(User user) {
    mailManager.send(
      user.getEmailAddress(),
      "Welcome to TaskAgile",
      "welcome.ftl",
      MessageVariable.from("user", user)
    );
  }
}
