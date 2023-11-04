package com.taskmanagement.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
  /**
   * login에 있는 index.html에 대한 get 매핑
   * @return index
   */
  @GetMapping(value={"/", "/login", "/register", "/board/*"})
  public String entry() {
    return "index";
  }
}
