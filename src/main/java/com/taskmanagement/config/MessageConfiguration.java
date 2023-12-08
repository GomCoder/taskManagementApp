package com.taskmanagement.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// 메시지 환경 설정
@Configuration
public class MessageConfiguration {
  @Bean
  public FanoutExchange domainEventsExchange() {
    return new FanoutExchange("ta.domain.events", true, false);
  }

  @Bean
  public Queue activityTrackingQueue() {
    return new Queue("ta.activity.tracking", true);
  }

  @Bean
  public Binding bindingActivityTracking(FanoutExchange exchange, Queue activityTrackingQueue) {
    return BindingBuilder.bind(activityTrackingQueue).to(exchange);
  }
}
