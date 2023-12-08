package com.taskmanagement.web.results;

import com.taskmanagement.domain.model.activity.Activity;
import org.springframework.http.ResponseEntity;

/**
 * 댓글에 활동 이력 결과 추가 
 */
public class CommentActivityResult {
  public static ResponseEntity<ApiResult> build(Activity activity) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", activity.getId().value())
      .add("cardId", activity.getCardId().value())
      .add("boardId", activity.getBoardId().value())
      .add("userId", activity.getUserId().value())
      .add("type", activity.getType().getType())
      .add("detail", activity.getDetail())
      .add("createdDate", activity.getCreatedDate().getTime());
    return Result.ok(apiResult);
  }
}
