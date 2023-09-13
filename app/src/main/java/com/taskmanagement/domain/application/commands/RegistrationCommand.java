package com.taskmanagement.domain.application.commands;

import org.springframework.util.Assert;

import java.util.Objects;

public class RegistrationCommand {

  private String username;
  private String emailAddress;
  private String password;

  /**
   * RegistrationCommand Constructor
   * @param username
   * @param emailAddress
   * @param password
   */
  public RegistrationCommand(String username, String emailAddress, String password) {
    Assert.hasText(username, "Parameter `username` must not be empty");
    Assert.hasText(emailAddress, "Parameter `emailAddress` must not be empty");
    Assert.hasText(password, "Parameter `password` must not be empty");

    this.username = username;
    this.emailAddress = emailAddress;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    RegistrationCommand that = (RegistrationCommand) object;
    return Objects.equals(username, that.username) && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    int result = username != null ? username.hashCode() : 0;

    result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);

    return result;
  }

  @Override
  public String toString() {
    return "RegistrationCommand{" +
      "username='" + username + '\'' +
      ", emailAddress='" + emailAddress + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
