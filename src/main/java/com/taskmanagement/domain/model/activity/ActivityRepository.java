package com.taskmanagement.domain.model.activity;

import com.taskmanagement.domain.model.card.CardId;

import java.util.List;

/**
 * 활동 이력 레포지토리 - 활동 이력 저장, 카드 활동 이력 조회
 */
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
