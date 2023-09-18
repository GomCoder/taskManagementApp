package com.taskmanagement.domain.model.board;

import com.taskmanagement.domain.model.team.TeamId;
import com.taskmanagement.domain.model.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardManagement {

  private BoardRepository boardRepository;
  private BoardMemberRepository boardMemberRepository;

  public BoardManagement(BoardRepository boardRepository, BoardMemberRepository boardMemberRepository) {
    this.boardRepository = boardRepository;
    this.boardMemberRepository = boardMemberRepository;
  }

  /**
   * 새로운 보드 생성
   * @param creatorId
   * @param name
   * @param description
   * @param teamId
   * @return
   */
  public Board createBoard(UserId creatorId, String name, String description, TeamId teamId) {
    Board board = Board.create(creatorId, name, description, teamId);
    boardRepository.save(board);
    /**
     * 보드 생성자로 작성자를 추가함
     */
    BoardMember boardMember = BoardMember.create(board.getId(), creatorId);
    boardMemberRepository.save(boardMember);
    return board;
  }
}
