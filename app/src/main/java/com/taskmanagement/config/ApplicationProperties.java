package com.taskmanagement.config;

/**
 * 원본 소스에서는 AWS S3에 파일 업로드 하는 방법과 Local에서 파일 업로드 하는 방법 2가지 구현 방법을 사용하고 있는데
 * 일단은 Local에서 파일 업로드 하는 부분만 구현하였음
 * AWS S3에 파일 업로드 구현은 https://github.com/taskagile/vuejs.spring-boot.mysql/commit/7cf27f2a8b76b388635077f3863ac9d8351fe154 참고할 것
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

  @NotNull
  private FileStorageSetting fileStorage;

  @NotNull
  private ImageSetting image;

  private CdnSetting cdn;

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

  public FileStorageSetting getFileStorage() {
    return fileStorage;
  }

  public void setFileStorage(FileStorageSetting fileStorage) {
    this.fileStorage = fileStorage;
  }

  public ImageSetting getImage() {
    return image;
  }

  public void setImage(ImageSetting image) {
    this.image = image;
  }

  public CdnSetting getCdn() {
    return cdn;
  }

  public void setCdn(CdnSetting cdn) {
    this.cdn = cdn;
  }

  /**
   * 파일 업로드를 위한 클래스 선언
   */
  private static class FileStorageSetting {
    private String localRootFolder;

    @NotBlank
    @NotEmpty
    private String tempFolder;

    @NotBlank
    @NotEmpty
    private String active;

    public String getLocalRootFolder() {
      return localRootFolder;
    }

    public void setLocalRootFolder(String localRootFolder) {
      this.localRootFolder = localRootFolder;
    }

    public String getTempFolder() {
      return tempFolder;
    }

    public void setTempFolder(String tempFolder) {
      this.tempFolder = tempFolder;
    }

    public String getActive() {
      return active;
    }

    public void setActive(String active) {
      this.active = active;
    }
  }

  private static class ImageSetting {
    @NotBlank
    @NotEmpty
    private String commandSearchPath;

    public String getCommandSearchPath() {
      return commandSearchPath;
    }

    public void setCommandSearchPath(String commandSearchPath) {
      this.commandSearchPath = commandSearchPath;
    }
  }

  private static class CdnSetting {
    private String url = "http://taskManagement.local";

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }

}
