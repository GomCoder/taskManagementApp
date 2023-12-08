package com.taskmanagement.domain.common.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
  /**
   * 파일 저장하기
   * @param folder 파일이 저장될 폴더 지정
   * @param file 저장할 파일
   * @return 저장된 파일의 경로
   */
  TempFile saveAsTempFile(String folder, MultipartFile file);

  /**
   * 임시 파일을 타겟 위치에 저장하기
   * @param tempFile 임시 파일
   */
  void saveTempFile(TempFile tempFile);

  /**
   * 업로드 파일을 타겟 위치에 저장하기
   * @param folder 파일이 저장될 폴더 지정
   * @param file 저장할 파일
   * @return 저장된 파일들의 경로
   */
  String saveUploaded(String folder, MultipartFile file);
}
