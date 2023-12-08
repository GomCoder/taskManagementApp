package com.taskmanagement.domain.model.attachment;

import com.taskmanagement.domain.common.file.FileStorage;
import com.taskmanagement.domain.common.file.TempFile;
import com.taskmanagement.utils.ImageUtils;
import io.jsonwebtoken.lang.Assert;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 이미지의 경우 썸네일 생성 컴포넌트
 */
@Component
public class ThumbnailCreator {
  private final static Logger log = LoggerFactory.getLogger(ThumbnailCreator.class);
  private final static Set<String> SUPPORTED_EXTENSIONS = new HashSet<>();
  private final static int MAX_WIDTH = 300;
  private final static int MAX_HEIGHT = 300;

  static {
    SUPPORTED_EXTENSIONS.add("png");
    SUPPORTED_EXTENSIONS.add("jpg");
    SUPPORTED_EXTENSIONS.add("jpeg");
  }

  /**
   * 썸네일 이미지 파일 저장소 생성
   * @param fileStorage 파일 저장소
   * @param tempImageFile 임시 이미지 저장 경로
   */
  public void create(FileStorage fileStorage, TempFile tempImageFile) {
    Assert.isTrue(tempImageFile.getFile().exists(), "이미지 파일 `" +
      tempImageFile.getFile().getAbsolutePath() + "`은(는) 존재해야 합니다");

    String ext = FilenameUtils.getExtension(tempImageFile.getFile().getName());
    if (!SUPPORTED_EXTENSIONS.contains(ext)) {
      throw new ThumbnailCreationException("썸네일을 생성할 수 없는 이미지 형식입니다");
    }

    log.debug("파일 `{}`에 대한 썸네일 생성 중", tempImageFile.getFile().getName());

    try {
      if (!isSupportedImageFormat(ext)) {
        throw new ThumbnailCreationException("썸네일을 생성할 수 없는 이미지 형식입니다");
      }

      log.debug("파일 `{}`에 대한 썸네일 생성 중", tempImageFile.getFile().getName());

      String sourceFilePath = tempImageFile.getFile().getAbsolutePath();
      String tempThumbnailFileImagePath = ImageUtils.getThumbnailVersion(sourceFilePath);
      Path tempThumbnailFilePath = Paths.get(tempThumbnailFileImagePath);

      try (InputStream inputStream = Files.newInputStream(Paths.get(sourceFilePath));
           OutputStream outputStream = Files.newOutputStream((tempThumbnailFilePath))) {
        Thumbnails.of(inputStream)
          .size(MAX_WIDTH, MAX_HEIGHT)
          .outputQuality(0.7)
          .toOutputStream(outputStream);

        fileStorage.saveTempFile(TempFile.create(tempImageFile.getRootTempPath(), tempThumbnailFilePath));
      }

      // 임시 썸네일 파일 삭제
      Files.delete(tempThumbnailFilePath);
    } catch (Exception e) {
      log.error("파일 `" + tempImageFile.getFile().getAbsolutePath() + "`에 대한 썸네일 생성 실패", e);
      throw new ThumbnailCreationException("썸네일 생성 실패", e);
    }
  }

  private boolean isSupportedImageFormat(String ext) {
    Set<String> supportedExtensions = new HashSet<>(Arrays.asList("png", "jpg", "jpeg"));
    return supportedExtensions.contains(ext.toLowerCase());
  }
}
