package com.taskmanagement.web.results;

import com.taskmanagement.domain.common.file.FileUrlCreator;
import com.taskmanagement.domain.model.attachment.Attachment;
import com.taskmanagement.utils.ImageUtils;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 복수의 첨부파일 처리에 대한 결과
 */
public class AttachmentResults {

  public static ResponseEntity<ApiResult> build(List<Attachment> attachments, FileUrlCreator fileUrlCreator) {
    List<ListableAttachment> result = new ArrayList<>();

    for (Attachment attachment :  attachments) {
      result.add(new ListableAttachment(attachment, fileUrlCreator));
    }
    ApiResult apiResult = ApiResult.blank().add("attachments", result);
    System.out.println("AttachmentResults.build() 호출..." + apiResult);
    return Result.ok(apiResult);
  }

  private static class ListableAttachment {
    private final long id;
    private final String fileName;
    private final String fileType;
    private final String fileUrl;
    private final String previewUrl;
    private final long userId;
    private final long createdDate;

    ListableAttachment(Attachment attachment, FileUrlCreator fileUrlCreator) {
      this.id = attachment.getId().value();
      this.fileName = attachment.getFileName();
      this.fileType = attachment.getFileType();
      this.fileUrl = fileUrlCreator.url(attachment.getFilePath());

      if (attachment.isThumbnailCreated()) {
        this.previewUrl = ImageUtils.getThumbnailVersion(this.fileUrl);
      } else {
        this.previewUrl = "";
      }

      this.userId = attachment.getUserId().value();
      this.createdDate = attachment.getCreatedDate().getTime();
    }

    public long getId() {
      return id;
    }

    public String getFileName() {
      return fileName;
    }

    public String getFileType() {
      return fileType;
    }

    public String getFileUrl() {
      return fileUrl;
    }

    public String getPreviewUrl() {
      return previewUrl;
    }

    public long getUserId() {
      return userId;
    }

    public long getCreatedDate() {
      return createdDate;
    }
  }
}


