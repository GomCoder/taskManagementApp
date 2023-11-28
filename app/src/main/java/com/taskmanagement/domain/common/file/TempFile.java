package com.taskmanagement.domain.common.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TempFile {
  private String rootTempPath;
  private String fileRelativePath;

  private TempFile(String rootTempPath, String fileRelativePath) {
    this.rootTempPath = rootTempPath;
    this.fileRelativePath = fileRelativePath;
  }

  public static TempFile create(String rootTempPath, Path targetLocation) {
    // targetLocation을 rootTempPath와 동일한 형식으로 변환
    Path rootPath = Paths.get(rootTempPath);
    String fileRelativePath = rootPath.relativize(targetLocation).toString();
    return new TempFile(rootTempPath, fileRelativePath);
  }

  public File getFile() {
    return new File(rootTempPath + File.separator + fileRelativePath);
  }

  public String getFileRelativePath() {
    return fileRelativePath;
  }

  public String getRootTempPath() {
    return rootTempPath;
  }
}
