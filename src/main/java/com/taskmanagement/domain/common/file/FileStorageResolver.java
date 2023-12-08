package com.taskmanagement.domain.common.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 활성 파일 저장 컴포넌트 
 */
@Component
public class FileStorageResolver {
  private final String activeStorageName;
  private final ApplicationContext applicationContext;

  public FileStorageResolver(@Value("${app.file-storage.active}") String activeStorageName,
                             ApplicationContext applicationContext) {
    this.activeStorageName = activeStorageName;
    this.applicationContext = applicationContext;
  }
  /**
   * application.properties의 활성 파일 저장소 구성
   */
  public FileStorage resolve() {
    return applicationContext.getBean(activeStorageName, FileStorage.class);
  }
}
