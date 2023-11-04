package com.taskmanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Configuration
@ConfigurationProperties(prefix = "app")
@Validated
public class ApplicationProperties {
  /**
   * 시스템에서 보낸 이메일 기본 From값
   */
  @Email
  @NotBlank
  private String mailFrom;

  @NotBlank
  @NotEmpty
  private String tokenSecretKey;

  @NotBlank
  @NotEmpty
  private String realTimeServerUrl;

  // Getter와 Setter
  public void setMailFrom(String mailFrom) {
    this.mailFrom = mailFrom;
  }

  public String getMailFrom() {
    return mailFrom;
  }

  public String getTokenSecretKey() {
    return tokenSecretKey;
  }

  public void setTokenSecretKey(String tokenSecretKey) {
    this.tokenSecretKey = tokenSecretKey;
  }

  public String getRealTimeServerUrl() {
    return realTimeServerUrl;
  }

  public void setRealTimeServerUrl(String realTimeServerUrl) {
    this.realTimeServerUrl = realTimeServerUrl;
  }
}
