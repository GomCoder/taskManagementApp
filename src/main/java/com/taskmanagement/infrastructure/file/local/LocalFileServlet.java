package com.taskmanagement.infrastructure.file.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 로컬 파일 저장 서블릿
 */
@WebServlet("/local-file/*")
public class LocalFileServlet extends HttpServlet implements Serializable {
  private static final Logger log = LoggerFactory.getLogger(LocalFileServlet.class);
  private static final long serialVersionUID = -4863045687710667973L;

  private final String localRootPath;
  private final Environment environment;

  public LocalFileServlet(@Value("${app.file-storage.local-root-folder}") String localRootPath, Environment environment) {
    this.localRootPath = localRootPath;
    this.environment = environment;
  }

  /**
   * 첨부파일 업로드 요청을 받아 파일 경로 생성
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (environment.acceptsProfiles("production", "staging")) {
      String activeProfiles = String.join(", ", environment.getActiveProfiles());
      log.warn("Access `{}` in environment. IP address: `{}` ", request.getPathInfo(), activeProfiles);
    }

    String pathInfo = request.getPathInfo();
    if ("/".equals(pathInfo)) {
      response.getWriter().write("/");
      return;
    }

    String filePath = localRootPath + request.getPathInfo();
    File file = new File(filePath);
    if (!file.exists() || file.isDirectory()) {
      response.sendError(404);
      return;
    }

    response.setContentType(request.getServletContext().getMimeType(pathInfo));
    response.setHeader("Cache-Control", "public, max-age=31536000");
    Files.copy(Paths.get(localRootPath, pathInfo), response.getOutputStream());
  }
}
