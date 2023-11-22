package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.team.TeamId;
import com.taskmanagement.domain.model.team.TeamRepository;
import com.taskmanagement.domain.model.user.UserId;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class HibernateTeamRepository extends HibernateSupport<Team> implements TeamRepository {

  HibernateTeamRepository(EntityManager entityManager) {
    super(entityManager);
  }


  @Override
  public List<Team> findTeamsByUserId(UserId userId) {
    String sql =
      "select t.* from team t where t.user_id = :userId " +
        " union " +
        " ( " +
        "   select t.* from team t, board b, board_member bm " +
        "   where t.id = b.team_id and bm.board_id and bm.user_id = :userId " +
        " ) ";
    NativeQuery<Team> query = getSession().createNativeQuery(sql, Team.class);
    query.setParameter("userId", userId.value());
    return query.list();
  }

  @Override
  public Team findById(TeamId teamId) {
    Query<Team> query = getSession().createQuery("from Team where id = :id", Team.class);
    query.setParameter("id", teamId.value());
    return query.uniqueResult();
  }
}
