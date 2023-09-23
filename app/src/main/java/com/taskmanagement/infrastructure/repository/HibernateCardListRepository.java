package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.cardlist.CardList;
import com.taskmanagement.domain.model.cardlist.CardListPosition;
import com.taskmanagement.domain.model.cardlist.CardListRepository;
import org.hibernate.query.NativeQuery;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HibernateCardListRepository extends HibernateSupport<CardList> implements CardListRepository {
  private JdbcTemplate jdbcTemplate;

  HibernateCardListRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
    super(entityManager);
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<CardList> findByBoardId(BoardId boardId) {
    String sql = "SELECT c1.* FROM card_list c1 WHERE c1.board_Id = :boarId";
    NativeQuery<CardList> query = getSession().createNativeQuery(sql, CardList.class);
    query.setParameter("boardId", boardId.value());
    return query.list();
  }

  @Override
  public void changePositions(final List<CardListPosition> cardListPositions) {
    String sql = " UPDATE card_list SET `position` = ? WHERE id = ?";

    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps, int i) throws SQLException {
        CardListPosition cardListPosition = cardListPositions.get(i);
        ps.setInt(1, cardListPosition.getPosition());
        ps.setLong(2, cardListPosition.getCardListId().value());
      }

      @Override
      public int getBatchSize() {
        return cardListPositions.size();
      }
    });
  }
}
