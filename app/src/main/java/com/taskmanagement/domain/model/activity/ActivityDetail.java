package com.taskmanagement.domain.model.activity;

import com.taskmanagement.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class ActivityDetail {
  private final Map<String, Object> detail = new HashMap<>();

  private ActivityDetail() {}
  static ActivityDetail blank() {
    return new ActivityDetail();
  }

  public ActivityDetail add(String key, Object value) {
    detail.put(key, value);
    return this;
  }

  String toJson() {
    return JsonUtils.toJson(detail);
  }
}
