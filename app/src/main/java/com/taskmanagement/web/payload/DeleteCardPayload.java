package com.taskmanagement.web.payload;

import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.cardlist.CardListId;

public class DeleteCardPayload {
  private long boardId;
  private long cardListId;
  private String title;
  private int position;

  public BoardId getBoardId() {
    return new BoardId(boardId);
  }

  public void setBoardId(long boardId) {
    this.boardId = boardId;
  }

  public void setCardListId(long cardListId) {
    this.cardListId = cardListId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPosition(int position) {
    this.position = position;
  }
}
