package com.taskmanagement.domain.model.cardlist;

import com.taskmanagement.domain.common.model.AbstractBaseEntity;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.user.UserId;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "card_list")
public class CardList extends AbstractBaseEntity {

  private Long id;
  private long boardId;
  private long userId;
  private String name;
  private int position;
  private boolean archived;
  private Date createdDate;

  public static CardList create(BoardId boardId, UserId userId, String name, int position) {
    CardList cardList = new CardList();
    cardList.boardId = boardId.value();
    cardList.userId = userId.value();
    cardList.name = name;
    cardList.position = position;
    cardList.archived = false;
    cardList.createdDate = new Date();
    return cardList;
  }

  public CardListId getId() {
    return new CardListId(id);
  }

  public BoardId getBoardId() {
    return new BoardId(boardId);
  }

  public UserId getUserId() {
    return UserId(userId);
  }

  public String getName() {
    return name;
  }

  public int getPosition() {
    return position;
  }

  public boolean isArchived() {
    return archived;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CardList cardList = (CardList) o;
    return boardId == cardList.boardId &&
      position == cardList.position &&
      archived == cardList.archived &&
      Objects.equals(name, cardList.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(boardId, name, position, archived);
  }

  @Override
  public String toString() {
    return "CardList{" +
      "id=" + id +
      ", boardId=" + boardId +
      ", userId=" + userId +
      ", name='" + name + '\'' +
      ", position=" + position +
      ", archived=" + archived +
      ", createdDate=" + createdDate +
      '}';
  }
}
