package com.taskmanagement.domain.application.impl;

import com.taskmanagement.domain.application.BoardService;
import com.taskmanagement.domain.application.commands.CreateBoardCommand;
import com.taskmanagement.domain.common.event.DomainEventPublisher;
import com.taskmanagement.domain.model.board.*;
import com.taskmanagement.domain.model.board.events.BoardCreatedEvent;
import com.taskmanagement.domain.model.board.events.BoardMemberAddedEvent;
import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserFinder;
import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.domain.model.user.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
  private final BoardRepository boardRepository;
  private final BoardManagement boardManagement;
  private final BoardMemberRepository boardMemberRepository;
  private final UserFinder userFinder;
  private final DomainEventPublisher domainEventPublisher;

  public BoardServiceImpl(BoardRepository boardRepository, BoardManagement boardManagement, BoardMemberRepository boardMemberRepository,UserFinder userFinder, DomainEventPublisher domainEventPublisher) {
    this.boardRepository = boardRepository;
    this.boardManagement = boardManagement;
    this.boardMemberRepository = boardMemberRepository;
    this.userFinder = userFinder;
    this.domainEventPublisher = domainEventPublisher;
  }


  @Override
  public List<Board> findBoardsByMembership(UserId userId) {
    return boardRepository.findBoardByMembership(userId);
  }

  @Override
  public Board findById(BoardId boardId) {
    return boardRepository.findById(boardId);
  }

  @Override
  public List<User> findMembers(BoardId boardId) {
    return boardMemberRepository.findMembers(boardId);
  }

  @Override
  public User addMember(BoardId boardId, String usernameOrEmailAddress) throws UserNotFoundException {
    User user = userFinder.find(usernameOrEmailAddress);
    boardMemberRepository.add(boardId, user.getId());
    domainEventPublisher.publish(new BoardMemberAddedEvent(this, boardId, user));
    return user;
  }
  @Override
  public Board createBoard(CreateBoardCommand command) {
    Board board = boardManagement.createBoard(command.getUserId(), command.getName(), command.getDescription(), command.getTeamId());
    domainEventPublisher.publish(new BoardCreatedEvent(this, board));
    return board;
  }
}
