package com.taskmanagement.domain.common.event;

import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.utils.IpAddress;

public interface TriggeredBy {
  /**
   * 이 명령을 트리거한 사용자의 ID 가져오기
   * @return 사용자 ID
   */
  UserId getUserId();

  /**
   * 요청한 IP 주소를 가져오기
   * @return 요청한 IP주소
   */
  IpAddress getIpAddress();
}
