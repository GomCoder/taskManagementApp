package com.taskmanagement.infrastructure.messaging;

import com.taskmanagement.domain.common.event.DomainEvent;
import com.taskmanagement.domain.common.event.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AmqpDomainEventPublisher implements DomainEventPublisher {
  private static final Logger log = LoggerFactory.getLogger(AmqpDomainEventPublisher.class);

  private final RabbitTemplate rabbitTemplate;
  private final FanoutExchange exchange;

  public AmqpDomainEventPublisher(RabbitTemplate rabbitTemplate, @Qualifier("domainEventsExchange") FanoutExchange exchange) {
    this.rabbitTemplate = rabbitTemplate;
    this.exchange = exchange;
  }

  @Override
  public void publish(DomainEvent event) {
    log.debug("Publishing domain event: " + event);
    try {
      rabbitTemplate.convertAndSend(exchange.getName(), "", event);
      System.out.println(exchange.getName());
    } catch (AmqpException e) {
      log.error("Failed to send domain event to MQ", e);
    }
  }
}
