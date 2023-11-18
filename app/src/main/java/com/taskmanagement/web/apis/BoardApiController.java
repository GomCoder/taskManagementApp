package com.taskmanagement.web.apis;

import com.taskmanagement.domain.application.BoardService;
import com.taskmanagement.domain.application.CardListService;
import com.taskmanagement.domain.application.CardService;
import com.taskmanagement.domain.application.TeamService;
import com.taskmanagement.domain.application.commands.AddBoardMemberCommand;
import com.taskmanagement.domain.application.commands.CreateBoardCommand;
import com.taskmanagement.domain.common.file.FileUrlCreator;
import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.domain.model.cardlist.CardList;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserNotFoundException;
import com.taskmanagement.web.payload.AddBoardMemberPayload;
import com.taskmanagement.web.payload.CreateBoardPayload;
import com.taskmanagement.web.results.ApiResult;
import com.taskmanagement.web.results.BoardResult;
import com.taskmanagement.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.taskmanagement.web.results.CreateBoardResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardApiController extends AbstractBaseController {
  private final BoardService boardService;
  private final TeamService teamService;
  private final CardListService cardListService;
  private final CardService cardService;
  private final FileUrlCreator fileUrlCreator;

  public BoardApiController(BoardService boardService,
                            TeamService teamService,
                            CardListService cardListService,
                            CardService cardService,
                            FileUrlCreator fileUrlCreator) {
    this.boardService = boardService;
    this.teamService = teamService;
    this.cardListService = cardListService;
    this.cardService = cardService;
    this.fileUrlCreator = fileUrlCreator;
  }

  @PostMapping("/api/boards")
  public ResponseEntity<ApiResult> createBoard(@RequestBody CreateBoardPayload payload,
                                               HttpServletRequest request) {
    System.out.println("POST: createBoard() 호출");
    CreateBoardCommand command = payload.toCommand();
    addTriggeredBy(command, request);
    Board board = boardService.createBoard(command);
    return CreateBoardResult.build(board);
  }

  @GetMapping("/api/boards/{boardId}")
  public ResponseEntity<ApiResult> getBoard(@PathVariable("boardId") long rawBoardId) {
    System.out.println("GET: getBoard() 호출");
    BoardId boardId = new BoardId(rawBoardId);
    Board board = boardService.findById(boardId);

    if(board == null) {
      return Result.notFound();
    }

    List<User> members = boardService.findMembers(boardId);

    Team team = null;

    if(!board.isPersonal()) {
      team = teamService.findById(board.getTeamId());
    }

    List<CardList> cardLists = cardListService.findByBoardId(boardId);
    List<Card> cards = cardService.findByBoardId(boardId);
    return BoardResult.build(team, board, members, cardLists, cards, fileUrlCreator);
  }

  @PostMapping(value="/api/boards/{boardId}/members")
  public ResponseEntity<ApiResult> addMember(@PathVariable("boardId") long rawBoardId,
                                             @RequestBody AddBoardMemberPayload payload,
                                             HttpServletRequest request) {
    System.out.println("POST: addMember() 호출");
    BoardId boardId = new BoardId(rawBoardId);
    Board board = boardService.findById(boardId);

    if (board == null) {
      return Result.notFound();
    }

    try {
      AddBoardMemberCommand command = payload.toCommand(boardId);
      addTriggeredBy(command, request);
      User member = boardService.addMember(command);

      ApiResult apiResult = ApiResult.blank()
        .add("id", member.getId().value())
        .add("shortName", member.getInitials());
      return Result.ok(apiResult);
    } catch (UserNotFoundException e) {
      return Result.failure("No user found");
    }
  }
}
