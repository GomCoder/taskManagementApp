package com.taskmanagement.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * 페이지 경로에 대한 메인 컨트롤러
 */
@Controller
public class MainController {
  /**
   * login에 있는 index.html에 대한 get 매핑
   * @return index
   */
  @GetMapping(value={"/", "/login", "/register", "/board/*", "/card/**"})
  public String entry() {
    return "index";
  }
}
