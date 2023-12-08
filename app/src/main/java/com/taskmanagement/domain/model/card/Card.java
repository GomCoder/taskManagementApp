package com.taskmanagement.domain.model.card;

import com.taskmanagement.domain.common.model.AbstractBaseEntity;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.cardlist.CardList;
import com.taskmanagement.domain.model.cardlist.CardListId;
import com.taskmanagement.domain.model.user.UserId;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 카드 엔터티
 */
@Entity
@Table(name = "card")
public class Card extends AbstractBaseEntity {
  private static final long serialVersionUID = -3399659220502240396L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name ="board_id")
  private long boardId;
  @Column(name = "card_list_id")
  private long cardListId;
  @Column(name = "user_id")
  private long userId;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description")
  private String description;

  @Column(name="cover_image")
  private String coverImage;
  @Column(name = "position")
  private int position;
  @Column(name = "archived")
  private boolean archived;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date", nullable = false)
  private Date createdDate;

  public static Card create(CardList cardList, UserId userId, String title, int position) {
    Card card = new Card();
    card.boardId = cardList.getBoardId().value();
    card.cardListId = cardList.getId().value();
    card.userId = userId.value();
    card.title = title;
    card.description = "";
    card.position = position;
    card.archived = false;
    card.createdDate = new Date();
    return card;
  }

  public void changeTitle(String title) {
    this.title = title;
  }

  public void changeDescription(String description) {
    this.description = description;
  }

  public CardId getId() {
    return new CardId(id);
  }

  public BoardId getBoardId() {
    return new BoardId(boardId);
  }
  public CardListId getCardListId() {
    return new CardListId(cardListId);
  }

  public UserId getUserId() {
    return new UserId(userId);
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public boolean hasCoverImage() {
    return StringUtils.hasText(coverImage);
  }

  public void addCoverImage(String coverImage) {
    this.coverImage = coverImage;
  }

  public String getCoverImage() {
    return coverImage;
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

  public void removeCoverImage() {
    this.coverImage = null;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Card)) return false;
    Card card = (Card) o;
    return cardListId == card.cardListId &&
      userId == card.userId &&
      position == card.position &&
      archived == card.archived &&
      Objects.equals(title, card.title);
  }


  @Override
  public int hashCode() {
    return Objects.hash(cardListId, userId, title, position, archived);
  }

  @Override
  public String toString() {
    return "Card{" +
      "id=" + id +
      ", boardId =" + boardId +
      ", cardListId=" + cardListId +
      ", userId=" + userId +
      ", title='" + title + '\'' +
      ", description='" + description + '\'' +
      ", coverImage='" + coverImage + '\'' +
      ", position=" + position +
      ", archived=" + archived +
      ", createdDate=" + createdDate +
      '}';
  }
}
