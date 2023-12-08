package com.taskmanagement.web.results;

import com.taskmanagement.domain.common.file.FileUrlCreator;
import com.taskmanagement.domain.model.attachment.Attachment;
import com.taskmanagement.utils.ImageUtils;
import org.springframework.http.ResponseEntity;

/**
 * 첨부파일 처리에 대한 결과
 */
public class AttachmentResult {

  public static ResponseEntity<ApiResult> build(Attachment attachment, FileUrlCreator fileUrlCreator) {
    String fileUrl = fileUrlCreator.url(attachment.getFilePath());
    ApiResult apiResult = ApiResult.blank()
      .add("id", attachment.getId().value())
      .add("fileName", attachment.getFileName())
      .add("fileType", attachment.getFileType())
      .add("fileUrl", attachment.getFilePath())
      .add("userId", attachment.getUserId().value())
      .add("createdDate", attachment.getCreatedDate().getTime());

    if (attachment.isThumbnailCreated()) {
      apiResult.add("previewUrl", ImageUtils.getThumbnailVersion(fileUrl));
    }

    return Result.ok(apiResult);
  }
}
