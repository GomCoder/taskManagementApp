package com.taskmanagement.config;

import com.taskmanagement.web.socket.WebSocketRequestDispatcher;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 웹 소켓 사용을 위한 환경 설정
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
  private final WebSocketRequestDispatcher requestDispatcher;

  public WebSocketConfiguration(WebSocketRequestDispatcher requestDispatcher) {
    this.requestDispatcher = requestDispatcher;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(requestDispatcher, "/rt").setAllowedOrigins("*").withSockJS();
  }
}
