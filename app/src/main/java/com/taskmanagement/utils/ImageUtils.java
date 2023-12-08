package com.taskmanagement.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.Assert;

/**
 * 이미지 경로 생성 유틸리티
 */
public final class ImageUtils {
  private ImageUtils() {}

  public static String getThumbnailVersion(String imagePath) {
    Assert.hasText(imagePath, "Parameter `imagePath` must not be blank");

    String ext = FilenameUtils.getExtension(imagePath);
    Assert.hasText(ext, "Image `" + imagePath + "` must have extension");

    return FilenameUtils.removeExtension(imagePath) + "_thumbnail." + ext;
  }

  public static boolean isImage(String contentType) {
    if (contentType == null) {
      return false;
    }
    return contentType.startsWith("image/");
  }
}
