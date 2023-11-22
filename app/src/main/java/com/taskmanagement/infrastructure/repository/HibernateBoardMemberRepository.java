package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.board.BoardMember;
import com.taskmanagement.domain.model.board.BoardMemberRepository;
import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserId;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateBoardMemberRepository extends HibernateSupport<BoardMember> implements BoardMemberRepository {
  HibernateBoardMemberRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<User> findMembers(BoardId boardId) {
    String sql =
      "select u.* from user u " +
        "left join board_member bm on u.id = bm.user_id " +
        "where bm.board_id = :boardId";
    NativeQuery<User> query = getSession().createNativeQuery(sql, User.class);
    query.setParameter("boardId", boardId.value());
    return query.list();
  }

  @Override
  public void add(BoardId boardId, UserId userId) {
    String sql = "insert ignore into board_member (board_id, user_id) value (:boardId, :userId)";
    NativeQuery query = getSession().createNativeQuery(sql);
    query.setParameter("boardId", boardId.value());
    query.setParameter("userId", userId.value());
    query.executeUpdate();
    System.out.println("------add() 호출...");
  }
}
