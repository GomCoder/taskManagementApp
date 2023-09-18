package com.taskmanagement.domain.model.team;

import com.taskmanagement.domain.common.model.AbstractBaseEntity;
import com.taskmanagement.domain.model.user.UserId;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "team")
public class Team extends AbstractBaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="name", nullable = false, length=128)
  private String name;
  @Column(name = "userId")
  private long userId;
  @Column(name = "archived")
  private boolean archived;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date", nullable = false)
  private Date createdDate;

  /**
   * 새로운 팀 생성하기
   * @param name
   * @param creatorId
   * @return
   */
  public static Team create(String name, UserId creatorId) {
    Team team = new Team();
    team.name = name;
    team.archived = false;
    team.userId = creatorId.value();
    team.createdDate = new Date();
    return team;
  }

  public TeamId getId() {
    return new TeamId(id);
  }

  public String getName() {
    return name;
  }

  public long getUserId() {
    return userId;
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
    if (!(o instanceof Team)) return false;
    Team team = (Team) o;
    return userId == team.userId && Objects.equals(name, team.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, userId);
  }

  @Override
  public String toString() {
    return "Team{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", userId=" + userId +
      ", archived=" + archived +
      ", createdDate=" + createdDate +
      '}';
  }
}
