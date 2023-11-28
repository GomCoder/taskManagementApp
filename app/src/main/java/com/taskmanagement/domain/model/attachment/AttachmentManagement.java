package com.taskmanagement.domain.model.attachment;

import com.taskmanagement.domain.common.file.FileStorage;
import com.taskmanagement.domain.common.file.FileStorageResolver;
import com.taskmanagement.domain.common.file.TempFile;
import com.taskmanagement.domain.model.card.CardId;
import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.utils.ImageUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class AttachmentManagement {
  private final static Logger log = LoggerFactory.getLogger(AttachmentManagement.class);

  private final FileStorageResolver fileStorageResolver;
  private final ThumbnailCreator thumbnailCreator;
  private final AttachmentRepository  attachmentRepository;

  public AttachmentManagement(FileStorageResolver fileStorageResolver,
                              ThumbnailCreator thumbnailCreator,
                              AttachmentRepository attachmentRepository) {
    this.fileStorageResolver = fileStorageResolver;
    this.thumbnailCreator = thumbnailCreator;
    this.attachmentRepository = attachmentRepository;
  }

  public Attachment save(CardId cardId, MultipartFile file, UserId userId) {
    System.out.println("AttachmentManagement.save() 호출...");
    FileStorage fileStorage = fileStorageResolver.resolve();

    String folder = "attachments";
    boolean thumbnailCreated = false;
    String filePath;
    String targetLocation = null;

    if (ImageUtils.isImage(file.getContentType())) {
      TempFile tempImageFile = fileStorage.saveAsTempFile(folder, file);
      fileStorage.saveTempFile(tempImageFile);
      thumbnailCreator.create(fileStorage, tempImageFile);

      try {
        // 이미지 파일은 이미 thumbnail 생성 후에 복사
        Files.copy(tempImageFile.getFile().toPath(), Paths.get(tempImageFile.getRootTempPath(), folder, tempImageFile.getFile().getName()));
        Files.delete(tempImageFile.getFile().toPath());
      } catch (IOException e) {
        log.error("임시 파일 복사 및 삭제 실패: `" + tempImageFile.getFile().getAbsolutePath() + "`", e);
      }

      filePath = tempImageFile.getFileRelativePath();
      thumbnailCreated = true;
    } else {
      filePath = fileStorage.saveUploaded(folder, file);
    }

    Attachment attachment = Attachment.create(cardId, userId, file.getOriginalFilename(), filePath, thumbnailCreated);
    attachment.setFilePath(generateThumbnailPath(filePath)); // 추가된 부분
    attachmentRepository.save(attachment);

    if (targetLocation != null) {
      log.debug("Files.copy 실행...");
      log.debug("Multipart file `{}` saved locally `{}`", file.getOriginalFilename(), targetLocation);
    }

    return attachment;
  }

  private String generateThumbnailPath(String filePath) {
    String extension = filePath.substring(filePath.lastIndexOf('.'));
    String thumbnailPath = filePath.replace(extension, "_thumbnail" + extension);
    return thumbnailPath;
  }

  /**
   * 이미지 저장하기
   * @param fileStorage 파일 저장소
   * @param folder 저장 폴더
   * @param file 파일
   * @return 임시 저장소 파일경로
   */
  private String saveImage(FileStorage fileStorage, String folder, MultipartFile file) {
    System.out.println("AttachmentManagement.saveImage() 호출...");
    TempFile tempImageFile = fileStorage.saveAsTempFile(folder, file);

    fileStorage.saveTempFile(tempImageFile);
    thumbnailCreator.create(fileStorage, tempImageFile);

    try {
      Files.delete(tempImageFile.getFile().toPath());
    } catch(IOException e) {
      log.error("Failed or delete temp file `" + tempImageFile.getFile().getAbsolutePath() + "`" + e);
    }
    return tempImageFile.getFileRelativePath();
  }

  protected String generateFileName(MultipartFile multipartFile) {
    System.out.println("AbstractBaseFileStorage.generateFileName() 호출...");
    String originalFileName = multipartFile.getOriginalFilename();
    String fileExtension = getFileExtension(originalFileName);
    String fileName = UUID.randomUUID().toString() + "." + fileExtension;
    return fileName;
  }
  private String getFileExtension(String fileName) {
    return FilenameUtils.getExtension(fileName);
  }


}
