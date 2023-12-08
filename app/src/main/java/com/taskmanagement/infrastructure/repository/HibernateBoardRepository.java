package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.board.BoardRepository;
import com.taskmanagement.domain.model.user.UserId;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 보드 Hibernate 레포지토리
 */
@Repository
public class HibernateBoardRepository extends HibernateSupport<Board> implements BoardRepository {
  HibernateBoardRepository(EntityManager entityManager) {
    super(entityManager);
  }

  /**
   * 사용자 아이디로 보드에 속한 멤버 조회
   */
  @Override
  public List<Board> findBoardsByMembership(UserId userId) {
    String sql = "select b.* from board b left join board_member bm on b.id = bm.board_id where bm.user_id = :userId";
    NativeQuery<Board> query = getSession().createNativeQuery(sql, Board.class);
    query.setParameter("userId", userId.value());
    return query.list();
  }

  /**
   * 보드 아이디로 보드 조회
   */
  @Override
  public Board findById(BoardId boardId) {
    Query<Board> query = getSession().createQuery("from Board where id = :id", Board.class);
    query.setParameter("id", boardId.value());
    return query.uniqueResult();
  }

  /**
   * 보드 삭제
   */
  @Override
  public Board deleteBoard(BoardId boardId) {
    Assert.notNull(boardId, "Parameter 'boardId' must not be null" );
    Session session = getSession();

    Board board = findById(boardId);

    if (board != null) {
      /**
       * 보드에 멤버가 존재하는 경우 -> 보드 멤버를 삭제
       */
      String deleteBoardMemberSql = "delete from board_member where board_id = :boardId";
      NativeQuery<?> deleteBoardMemberQuery = session.createNativeQuery(deleteBoardMemberSql);
      deleteBoardMemberQuery.setParameter("boardId", boardId.value());
      deleteBoardMemberQuery.executeUpdate();

      /**
       * 보드 삭제 수행
       */
      String deleteBoardSql = "delete from board where id = :boardId";
      NativeQuery<?> deleteBoardQuery = session.createNativeQuery(deleteBoardSql);
      deleteBoardQuery.setParameter("boardId", boardId.value());
      deleteBoardQuery.executeUpdate();
    }
    return getSession().find(Board.class, boardId.value());
  }
}
