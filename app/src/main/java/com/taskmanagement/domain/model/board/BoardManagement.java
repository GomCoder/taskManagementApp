package com.taskmanagement.domain.model.board;

import com.taskmanagement.domain.model.team.TeamId;
import com.taskmanagement.domain.model.user.UserId;
import org.springframework.stereotype.Component;

@Component
public class BoardManagement {

  private final BoardRepository boardRepository;
  private final BoardMemberRepository boardMemberRepository;

  public BoardManagement(BoardRepository boardRepository, BoardMemberRepository boardMemberRepository) {
    this.boardRepository = boardRepository;
    this.boardMemberRepository = boardMemberRepository;
  }

  /**
   * 새로운 보드 생성
   * @param creatorId 보드 생성자 아이디
   * @param name 보드 이름
   * @param description 보드 설명
   * @param teamId 팀 아이디
   * @return 생성된 보드 객체 반환
   */
  public Board createBoard(UserId creatorId, String name, String description, TeamId teamId) {
    Board board = Board.create(creatorId, name, description, teamId);
    System.out.println("Board.createBoard() 생성자 호출: " + teamId.value());
    boardRepository.save(board);
    /**
     * 보드 생성자로 작성자를 추가함
     */
    BoardMember boardMember = BoardMember.create(board.getId(), creatorId);
    boardMemberRepository.save(boardMember);
    return board;
  }
}
