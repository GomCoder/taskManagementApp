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

@Repository
public class HibernateBoardRepository extends HibernateSupport<Board> implements BoardRepository {
  HibernateBoardRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Board> findBoardsByMembership(UserId userId) {
    String sql = "select b.* from board b left join board_member bm on b.id = bm.board_id where bm.user_id = :userId";
    NativeQuery<Board> query = getSession().createNativeQuery(sql, Board.class);
    query.setParameter("userId", userId.value());
    return query.list();
  }

  @Override
  public Board findById(BoardId boardId) {
    Query<Board> query = getSession().createQuery("from Board where id = :id", Board.class);
    query.setParameter("id", boardId.value());
    return query.uniqueResult();
  }

  @Override
  public Board deleteBoard(BoardId boardId) {
    Assert.notNull(boardId, "Parameter 'boardId' must not be null" );
    Session session = getSession();

    // Fetch the board to be deleted
    Board board = findById(boardId);

    // Check if the board exists
    if (board != null) {
      // Delete associated records in board_member table
      String deleteBoardMemberSql = "delete from board_member where board_id = :boardId";
      NativeQuery<?> deleteBoardMemberQuery = session.createNativeQuery(deleteBoardMemberSql);
      deleteBoardMemberQuery.setParameter("boardId", boardId.value());
      deleteBoardMemberQuery.executeUpdate();

      // Delete the board
      String deleteBoardSql = "delete from board where id = :boardId";
      NativeQuery<?> deleteBoardQuery = session.createNativeQuery(deleteBoardSql);
      deleteBoardQuery.setParameter("boardId", boardId.value());
      deleteBoardQuery.executeUpdate();
    }
    return getSession().find(Board.class, boardId.value());
  }
}
