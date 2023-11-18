package com.taskmanagement.domain.application;

import com.taskmanagement.domain.model.activity.Activity;

public interface ActivityService {
  /**
   * 활동 이력 저장하기
   * @param activity 활동 이력 정보
   */
  void saveActivity(Activity activity);
}
