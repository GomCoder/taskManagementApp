package com.taskmanagement.web.results;

import com.taskmanagement.domain.model.activity.Activity;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 카드 활동 이력 처리에 대한 결과
 */
public class CardActivitiesResult {

  public static ResponseEntity<ApiResult> build(List<Activity> activities) {
    List<ListableActivity> result = new ArrayList<>();

    for (Activity activity : activities) {
      result.add(new ListableActivity(activity));
    }

    ApiResult apiResult = ApiResult.blank()
      .add("activities", result);
    return Result.ok(apiResult);
  }

  private static class ListableActivity {
    private final long id;
    private final String type;
    private final String detail;
    private final long userId;
    private final long createdDate;

    ListableActivity(Activity activity) {
      this.id = activity.getId().value();
      this.type  = activity.getType().getType();
      this.detail = activity.getDetail();
      this.userId = activity.getUserId().value();
      this.createdDate = activity.getCreatedDate().getTime();
    }

    public long getId() {
      return id;
    }

    public String getType() {
      return type;
    }

    public String getDetail() {
      return detail;
    }

    public long getUserId() {
      return userId;
    }

    public long getCreatedDate() {
      return createdDate;
    }
  }
}
