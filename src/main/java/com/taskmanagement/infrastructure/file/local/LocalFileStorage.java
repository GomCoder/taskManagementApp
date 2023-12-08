package com.taskmanagement.infrastructure.file.local;
/**
 * Local-Storage에 저장하기 위한 Component
 * AWS S3에 저장하기 위한 Component 구현은 https://github.com/taskagile/vuejs.spring-boot.mysql/commit/7cf27f2a8b76b388635077f3863ac9d8351fe154 참고
 */

import com.taskmanagement.domain.common.file.AbstractBaseFileStorage;
import com.taskmanagement.domain.common.file.FileStorageException;
import com.taskmanagement.domain.common.file.TempFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 로컬 파일 저장소 생성
 */
@Component("localFileStorage")
public class LocalFileStorage extends AbstractBaseFileStorage {
  private final String rootFolderPath;
  private final String rootTempPath;

  public LocalFileStorage(@Value("${app.file-storage.local-root-folder}") String rootPath, @Value("${app.file-storage.temp-folder}") String tempPath) {
    this.rootFolderPath = rootPath;
    this.rootTempPath = tempPath;
  }

  /**
   * 임시 파일 폴더에 저장
   */
  @Override
  public TempFile saveAsTempFile(String folder, MultipartFile multipartFile) {
    return saveMultipartFileToLocalTempFolder(rootTempPath, folder, multipartFile);
  }

  /**
   * 임시 파일 저장
   */
  @Override
  public void saveTempFile(TempFile tempFile) {
    Path targetLocation = Paths.get(rootFolderPath + "/" + tempFile.getFileRelativePath());
    try {
      Files.createDirectories(targetLocation);
      Files.copy(tempFile.getFile().toPath(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
    } catch(IOException e) {
      throw new FileStorageException("Failed to save temp file", e);
    }
  }

  /**
   * 업로드된 파일을 로컬에 저장
   */
  @Override
  public String saveUploaded(String folder, MultipartFile multipartFile) {
    TempFile locallySavedFile = saveMultipartFileToLocalTempFolder(rootFolderPath, folder, multipartFile);
    return locallySavedFile.getFileRelativePath();
  }
}
