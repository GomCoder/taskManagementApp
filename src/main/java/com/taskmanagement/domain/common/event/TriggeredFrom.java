package com.taskmanagement.domain.common.event;

import com.taskmanagement.utils.IpAddress;

public interface TriggeredFrom {
  /**
   * 요청이 발생한 IP 주소를 가져옵니다
   * @return 요청이 발생한 IP 주소
   */
  IpAddress getIpAddress();
}
