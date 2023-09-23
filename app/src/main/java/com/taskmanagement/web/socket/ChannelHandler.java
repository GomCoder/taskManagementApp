package com.taskmanagement.web.socket;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ChannelHandler {
  /**
   * 채널 패턴, 값()
   * @return
   */
  String pattern() default "";

  /**
   * 처리기가 사용할 매핑할 채널 패턴
   * @return
   */
  String value() default "";
}
