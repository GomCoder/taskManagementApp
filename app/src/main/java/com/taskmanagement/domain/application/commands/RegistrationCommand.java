package com.taskmanagement.domain.application.commands;

import org.springframework.util.Assert;

import java.util.Objects;

public class RegistrationCommand {

  private String username;
  private String emailAddress;
  private String firstName;
  private String lastName;
  private String password;

  /**
   * RegistrationCommand Constructor
   * @param username
   * @param emailAddress
   * @param password
   */
  public RegistrationCommand(String username, String emailAddress, String firstName, String lastName ,String password) {
    Assert.hasText(username, "Parameter `username` must not be empty");
    Assert.hasText(emailAddress, "Parameter `emailAddress` must not be empty");
    Assert.hasText(firstName, "Parameter `firstName` must not be empty");
    Assert.hasText(lastName, "Parameter `lastName` must not be empty");
    Assert.hasText(password, "Parameter `password` must not be empty");

    this.username = username;
    this.emailAddress = emailAddress;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getEmailAddress() {
    return emailAddress;
  }
  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPassword() {
    return this.password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RegistrationCommand that = (RegistrationCommand) o;
    return Objects.equals(username, that.username) &&
      Objects.equals(emailAddress, that.emailAddress) &&
      Objects.equals(firstName, that.firstName) &&
      Objects.equals(lastName, that.lastName) &&
      Objects.equals(password, that.password);
  }

//  @Override
//  public int hashCode() {
//    int result = username != null ? username.hashCode() : 0;
//
//    result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
//    result = 31 * result + (password != null ? password.hashCode() : 0);
//
//    return result;
//  }


  @Override
  public int hashCode() {
    return Objects.hash(username, emailAddress, firstName, lastName, password);
  }

  @Override
  public String toString() {
    return "RegistrationCommand{" +
      "username='" + username + '\'' +
      ", emailAddress='" + emailAddress + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
