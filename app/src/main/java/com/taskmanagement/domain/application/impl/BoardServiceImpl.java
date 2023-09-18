package com.taskmanagement.domain.application.impl;

import com.taskmanagement.domain.application.BoardService;
import com.taskmanagement.domain.application.commands.CreateBoardCommand;
import com.taskmanagement.domain.common.event.DomainEventPublisher;
import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.board.BoardManagement;
import com.taskmanagement.domain.model.board.BoardRepository;
import com.taskmanagement.domain.model.board.event.BoardCreatedEvent;
import com.taskmanagement.domain.model.user.UserId;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
  private BoardRepository boardRepository;
  private BoardManagement boardManagement;
  private DomainEventPublisher domainEventPublisher;

  public BoardServiceImpl(BoardRepository boardRepository, BoardManagement boardManagement, DomainEventPublisher domainEventPublisher) {
    this.boardRepository = boardRepository;
    this.boardManagement = boardManagement;
    this.domainEventPublisher = domainEventPublisher;
  }


  @Override
  public List<Board> findBoardsByMembership(UserId userId) {
    return boardRepository.findBoardByMembership(userId);
  }

  @Override
  public Board createBoard(CreateBoardCommand command) {
    Board board = boardManagement.createBoard(command.getUserId(), command.getName(), command.getDescription(), command.getTeamId());
    domainEventPublisher.publish(new BoardCreatedEvent(this, board));
    return board;
  }
}
