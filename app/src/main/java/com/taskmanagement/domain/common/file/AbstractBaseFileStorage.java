package com.taskmanagement.domain.common.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractBaseFileStorage implements FileStorage {

  private static final Logger log = LoggerFactory.getLogger(AbstractBaseFileStorage.class);

  protected TempFile saveMultipartFileToLocalTempFolder(String rootTempPath, String folder, MultipartFile multipartFile) {
    System.out.println("AbstractBaseFileStorage.saveMultipartFileToLocalTempFolder() 호출...");
    Path storagePath = Paths.get(rootTempPath, folder).toAbsolutePath().normalize();

    try {
      Files.createDirectories(storagePath);
    } catch(IOException e) {
      throw new FileStorageException("Failed to create folder where the upload file will be stored", e);
    }

    String finalFileName = generateFileName(multipartFile);
    Path targetLocation = storagePath.resolve(finalFileName);

    log.debug("storagePath: {}", storagePath);
    log.debug("targetLocation: {}", targetLocation);

    try {
      Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      System.out.println("Files.copy 실행...");
      log.debug("Multipart file `{}` saved locally `{}`", multipartFile.getOriginalFilename(), targetLocation);
    } catch (IOException e) {
      throw new FileStorageException("Failed to save multipart file to `" + targetLocation.toString() + "`", e);
    }
    return TempFile.create(rootTempPath, targetLocation);
  }

  protected String generateFileName(MultipartFile multipartFile) {
    System.out.println("AbstractBaseFileStorage.generateFileName() 호출...");
    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    System.out.println("fileName: " + fileName);

    if (fileName.contains("..")) {
      throw new FileStorageException("Invalid file name `" + fileName + "`");
    }
    String timestamp = String.valueOf(new Date().getTime());
    String uuid = UUID.randomUUID().toString();
    String ext = FilenameUtils.getExtension(fileName);
    return timestamp + "." + uuid + (StringUtils.hasText(ext) ? ("." + ext) : "");
  }
}