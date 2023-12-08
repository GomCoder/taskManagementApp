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

/**
 * 보드 멤버 Hiberante 레포지토리 
 */
@Repository
public class HibernateBoardMemberRepository extends HibernateSupport<BoardMember> implements BoardMemberRepository {
  HibernateBoardMemberRepository(EntityManager entityManager) {
    super(entityManager);
  }

  /**
   * 보드 아이디로 멤버 조회
   */
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

  /**
   * 보드 추가
   */
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
