package com.taskmanagement.domain.application.impl;

import com.taskmanagement.domain.application.ActivityService;
import com.taskmanagement.domain.model.activity.Activity;
import com.taskmanagement.domain.model.activity.ActivityRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

  private final ActivityRepository activityRepository;

  public ActivityServiceImpl(ActivityRepository activityRepository) {
    this.activityRepository = activityRepository;
  }

  @Override
  public void saveActivity(Activity activity) {
    activityRepository.save(activity);
  }
}
