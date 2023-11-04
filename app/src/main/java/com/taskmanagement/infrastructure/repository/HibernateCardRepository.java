package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.domain.model.card.CardPosition;
import com.taskmanagement.domain.model.card.CardRepository;
import org.hibernate.query.NativeQuery;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HibernateCardRepository extends HibernateSupport<Card> implements CardRepository {
  private JdbcTemplate jdbcTemplate;

  HibernateCardRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
    super(entityManager);
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Card> findByBoardId(BoardId boardId) {
    String sql = "SELECT c.* FROM card c LEFT JOIN card_list c1 ON c.card_list_id = c1.id WHERE c1.board_id = :boardId";
    NativeQuery<Card> query = getSession().createNativeQuery(sql, Card.class);
    query.setParameter("boardId", boardId.value());
    return query.list();
  }

  @Override
  public void changePositions(final List<CardPosition> cardPositions) {
    String sql = " UPDATE card SET card_list_id = ? , `position` = ?  WHERE id = ?";
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps, int i) throws SQLException {
        CardPosition cardPosition = cardPositions.get(i);
        ps.setLong(1, cardPosition.getCardListId().value());
        ps.setInt(2, cardPosition.getPosition());
        ps.setLong(3, cardPosition.getCardId().value());
      }

      @Override
      public int getBatchSize() {
        return cardPositions.size();
      }
    });
  }
}
