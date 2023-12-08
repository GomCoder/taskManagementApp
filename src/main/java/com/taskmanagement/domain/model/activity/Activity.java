package com.taskmanagement.domain.model.activity;

import com.taskmanagement.domain.common.model.AbstractBaseEntity;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.card.CardId;
import com.taskmanagement.domain.model.user.UserId;
import com.taskmanagement.utils.IpAddress;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 활동 이력 엔터티
 */
@Entity
@Table(name="activity")
public class Activity extends AbstractBaseEntity {
  private static final long serialVersionUID = -1074755070833214461L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private long userId;

  @Column(name = "card_id")
  private Long cardId;

  @Column(name = "board_Id")
  private Long boardId;

  @Column(name ="type")
  @Enumerated(EnumType.ORDINAL)
  private ActivityType type;

  @Column(name="detail")
  private String detail;
  @Column(name = "ip_address")
  private String ipAddress;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name ="created_date", nullable=false)
  private Date createdDate;

  private Activity(UserId userId,
                   @Nullable CardId cardId,
                   @Nullable BoardId boardId,
                   ActivityType type,
                   String detail,
                   IpAddress ipAddress) {

    this.userId = userId.value();
    this.cardId = cardId != null ? cardId.value() : null;
    this.boardId = boardId != null ? boardId.value(): null;
    this.type = type;
    this.detail = detail;
    this.ipAddress = ipAddress.value();
    this.createdDate = new Date();
  }

  public Activity() {}

  public static Activity from(UserId userId,
                              BoardId boardId,
                              ActivityType type,
                              String detail,
                              IpAddress ipAddress) {
    return new Activity(userId, null, boardId, type, detail, ipAddress);
  }

  public static Activity from(UserId userId,
                              CardId cardId,
                              BoardId boardId,
                              ActivityType type,
                              String detail,
                              IpAddress ipAddress) {
    return new Activity(userId, cardId, boardId, type, detail, ipAddress);
  }

  public ActivityId getId() {
    return new ActivityId(id);
  }

  public IpAddress getIpAddress() {
    return new IpAddress(ipAddress);
  }

  public UserId getUserId() {
    return new UserId(userId);
  }

  public CardId getCardId() {
    return cardId == null ? null : new CardId(cardId);
  }

  public BoardId getBoardId() {
    return boardId ==  null ? null : new BoardId(boardId);
  }

  public ActivityType getType() {
    return type;
  }

  public String getDetail() {
    return detail;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Activity)) return false;
    Activity activity = (Activity) o;
    return userId == activity.userId &&
          type == activity.type &&
          Objects.equals(cardId, activity.cardId) &&
          Objects.equals(boardId, activity.boardId) &&
          Objects.equals(detail, activity.detail) &&
          Objects.equals(createdDate, activity.createdDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, cardId, boardId, type, detail, ipAddress, createdDate);
  }

  @Override
  public String toString() {
    return "Activity{" +
      "id=" + id +
      ", userId=" + userId +
      ", cardId=" + cardId +
      ", boardId=" + boardId +
      ", type=" + type +
      ", detail='" + detail + '\'' +
      ", ipAddress=`" + ipAddress + '\'' +
      ", createdDate=" + createdDate +
      '}';
  }
}
