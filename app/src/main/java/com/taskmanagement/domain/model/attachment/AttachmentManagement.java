package com.taskmanagement.domain.model.attachment;

import com.taskmanagement.domain.common.file.FileStorage;
import com.taskmanagement.domain.common.file.FileStorageResolver;
import com.taskmanagement.domain.common.file.TempFile;
import com.taskmanagement.domain.model.card.CardId;
import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@Component
public class AttachmentManagement {
  private final static Logger log = LoggerFactory.getLogger(AttachmentManagement.class);

  private final FileStorageResolver fileStorageResolver;
  //private final ThumbnailCreator thumbnailCreator;
  private final AttachmentRepository  attachmentRepository;

//  public AttachmentManagement(FileStorageResolver fileStorageResolver,
//                              ThumbnailCreator thumbnailCreator,
//                              AttachmentRepository attachmentRepository) {
//    this.fileStorageResolver = fileStorageResolver;
//    //this.thumbnailCreator = thumbnailCreator;
//    this.attachmentRepository = attachmentRepository;
//  }

  public AttachmentManagement(FileStorageResolver fileStorageResolver,
                              AttachmentRepository attachmentRepository) {
    this.fileStorageResolver = fileStorageResolver;
    this.attachmentRepository = attachmentRepository;
  }

  public Attachment save(CardId cardId, MultipartFile file, UserId userId) {
    System.out.println("AttachmentManagement.save() 호출...");
    FileStorage fileStorage = fileStorageResolver.resolve();

    String filePath;
    String folder = "attachments";
    boolean thumbnailCreated = false;

//    if (ImageUtils.isImage(file.getContentType())) {
//      filePath = saveImage(fileStorage, folder, file);
//
//      thumbnailCreated = true;
//    } else {
//      filePath = fileStorage.saveUploaded(folder, file);
//    }

    filePath = fileStorage.saveUploaded(folder, file);

    Attachment attachment = Attachment.create(cardId, userId, file.getOriginalFilename(), filePath, thumbnailCreated);
    attachmentRepository.save(attachment);
    return attachment;
  }

  /**
   * 이미지 저장하기
   * @param fileStorage 파일 저장소
   * @param folder 저장 폴더
   * @param file 파일
   * @return 임시 저장소 파일경로
   */
//  private String saveImage(FileStorage fileStorage, String folder, MultipartFile file) {
//    System.out.println("AttachmentManagement.saveImage() 호출...");
//    TempFile tempImageFile = fileStorage.saveAsTempFile(folder, file);
//
//    fileStorage.saveTempFile(tempImageFile);
//    thumbnailCreator.create(fileStorage, tempImageFile);
//
//    try {
//      Files.delete(tempImageFile.getFile().toPath());
//    } catch(IOException e) {
//      log.error("Failed or delete temp file `" + tempImageFile.getFile().getAbsolutePath() + "`" + e);
//    }
//    return tempImageFile.getFileRelativePath();
//  }
}
