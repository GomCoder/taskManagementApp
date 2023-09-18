package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.BoardService;
import com.taskmanagement.domain.common.security.CurrentUser;
import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.user.SimpleUser;
import com.taskmanagement.web.payload.CreateBoardPayload;
import com.taskmanagement.web.results.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.taskmanagement.web.results.CreateBoardResult;

@Controller
public class BoardApiController {
  private BoardService boardService;

  public BoardApiController(BoardService boardService) {
    this.boardService = boardService;
  }

  @PostMapping("/api/boards")
  public ResponseEntity<ApiResult> createBoard(@RequestBody CreateBoardPayload payload, @CurrentUser SimpleUser currentUser) {
    Board board = boardService.createBoard(payload.toCommand(currentUser.getUserId()));
    return CreateBoardResult.build(board);
  }
}
