package com.taskmanagement.domain.model.attachment;

import com.taskmanagement.domain.common.file.FileStorage;
import com.taskmanagement.domain.common.file.TempFile;
import com.taskmanagement.utils.ImageUtils;
import com.taskmanagement.utils.Size;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

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

  private final ImageProcessor imageProcessor;

  public ThumbnailCreator(ImageProcessor imageProcessor) {
    this.imageProcessor = imageProcessor;
  }

  /**
   * 썸네일 이미지 파일 저장소 생성
   * @param fileStorage 파일 저장소
   * @param tempImageFile 임시 이미지 저장 경로
   */
  public void create(FileStorage fileStorage, TempFile tempImageFile) {
    Assert.isTrue(tempImageFile.getFile().exists(), "Image file `" +
      tempImageFile.getFile().getAbsolutePath() + "` must exist");

    String ext = FilenameUtils.getExtension(tempImageFile.getFile().getName());
    if (!SUPPORTED_EXTENSIONS.contains(ext)) {
      throw new ThumbnailCreationException("Not supported image format for creating thumbnail");
    }

    log.debug("Creating thumbnail for file `{}`", tempImageFile.getFile().getName());

    try {
      String sourceFilePath = tempImageFile.getFile().getAbsolutePath();
      if (!sourceFilePath.endsWith("." + ext)) {
        throw new IllegalArgumentException("Image file's ext doesn't match the one in file descriptor");
      }
      String tempThumbnailFilePath = ImageUtils.getThumbnailVersion(tempImageFile.getFile().getAbsolutePath());
      Size resizeTo = getTargetSize(sourceFilePath);
      imageProcessor.resize(sourceFilePath, tempThumbnailFilePath, resizeTo);

      fileStorage.saveTempFile(TempFile.create(tempImageFile.tempRootPath(), Paths.get(tempThumbnailFilePath)));
      // Delete temp thumbnail file
      Files.delete(Paths.get(tempThumbnailFilePath));
    } catch (Exception e) {
      log.error("Failed to create thumbnail for file `" + tempImageFile.getFile().getAbsolutePath() + "`", e);
      throw new ThumbnailCreationException("Creating thumbnail failed", e);
    }
  }

  private Size getTargetSize(String imageFilePath) throws IOException {
    System.out.println("ThumbnailCreator.getTargetSize() 호출...");
    Size actualSize = imageProcessor.getSize(imageFilePath);

    if (actualSize.getWidth() <= MAX_WIDTH && actualSize.getHeight() <= MAX_HEIGHT) {
      return actualSize;
    }

    if (actualSize.getWidth() > actualSize.getHeight()) {
      int width = MAX_WIDTH;
      int height = (int) Math.floor(((double) width / (double)actualSize.getWidth()) * actualSize.getHeight());
      return new Size(width, height);
    } else {
      int height = MAX_HEIGHT;
      int width = (int) Math.floor(((double) height / (double) actualSize.getHeight()) * actualSize.getWidth());
      return new Size(width, height);
    }
  }
}