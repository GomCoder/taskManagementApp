package com.taskmanagement.web.socket;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
  /**
   * 동작 패턴. 정확한 일치가 필요함
   * @return
   */
  String value() default "";
}
