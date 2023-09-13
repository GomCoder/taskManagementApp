package com.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class JpaConfig {
  @PersistenceContext
  private EntityManager entityManager;

  @Bean
  public EntityManager entityManager() {
    return this.entityManager;
  }
}
