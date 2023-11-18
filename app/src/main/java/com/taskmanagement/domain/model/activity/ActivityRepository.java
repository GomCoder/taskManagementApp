package com.taskmanagement.domain.model.activity;

import com.taskmanagement.domain.model.card.CardId;

import java.util.List;

public interface ActivityRepository {
  /**
   * 활동 이력 저장하기
   * @param activity
   */
  void save(Activity activity);

  /**
   * 카드의 활동 이력 가져오기
   * @param cardId
   * @return
   */
  List<Activity> findCardActivities(CardId cardId);
}
