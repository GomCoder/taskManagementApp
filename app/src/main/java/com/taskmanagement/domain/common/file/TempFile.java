package com.taskmanagement.domain.common.file;

import java.io.File;
import java.nio.file.Path;

public class TempFile {
  private String rootTempPath;
  private String fileRelativePath;

  public static TempFile create(String rootTempPath, Path fileAbsolutePath) {
    System.out.println("TempFile.create() 호출...");
    TempFile tempFile = new TempFile();
    tempFile.rootTempPath = rootTempPath;
    tempFile.fileRelativePath = fileAbsolutePath.toString().replaceFirst(rootTempPath + "/", "");
    System.out.println("tempFile.rootTempFilePath: " + tempFile.rootTempPath);
    System.out.println("tempFile.fileRelativePath: " + tempFile.fileRelativePath);
    return tempFile;
  }

  public File getFile() {
    return new File(rootTempPath + "/" + fileRelativePath);
  }

  public String getFileRelativePath() {
    return fileRelativePath;
  }

  public String tempRootPath() {
    return rootTempPath;
  }
}
