package com.taskmanagement.web.socket.handler;

import com.taskmanagement.web.socket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 보드 페이지 접근에 사용할 구독, 비구독 채널 핸들러
 */
@ChannelHandler("/board/*")
public class BoardChannelHandler {
  private static final Logger log = LoggerFactory.getLogger(BoardChannelHandler.class);

  @Action("subscribe")
  public void subscribe(RealTimeSession session, @ChannelValue String channel) {
    log.debug("RealTimeSession[{}] Subscribe to channel `{}`", session.id(), channel);
    SubscriptionHub.subscribe(session, channel);
  }

  @Action("unsubscribe")
  public void unsubscribe(RealTimeSession session, @ChannelValue String channel) {
    log.debug("RealTimeSession[{}] Unsubscribe from channel `{}`", session.id(), channel);
    SubscriptionHub.unsubscribe(session, channel);
  }
}
