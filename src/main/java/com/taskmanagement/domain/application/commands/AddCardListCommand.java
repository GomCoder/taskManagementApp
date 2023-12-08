package com.taskmanagement.domain.application.commands;

import com.taskmanagement.domain.model.board.BoardId;

/**
 * 카드 리스트를 추가하는 커멘드 클래스
 */
public class AddCardListCommand extends UserCommand {
  private final String name;
  private final BoardId boardId;
  private final int position;

  public AddCardListCommand(BoardId boardId, String name, int position) {
    this.boardId = boardId;
    this.name = name;
    this.position = position;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public String getName() {
    return name;
  }

  public int getPosition() {
    return position;
  }
}
