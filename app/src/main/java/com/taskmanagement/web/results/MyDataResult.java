package com.taskmanagement.web.results;

import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.user.User;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDataResult {

  public static ResponseEntity<ApiResult> build(User user,
                                                List<Team> teams,
                                                List<Board> boards,
                                                String realTimeServerUrl,
                                                String realTimeToken) {
    Map<String, Object> userData = new HashMap<>();
    userData.put("name", user.getFirstName() + " " + user.getLastName());
    userData.put("token", realTimeToken);

    Map<String, Object> settings = new HashMap<>();
    settings.put("realTimeServerUrl", realTimeServerUrl);

    List<TeamResult> teamResults = new ArrayList<>();

    for(Team team: teams) {
      teamResults.add(new TeamResult(team));
    }

    List<BoardResult> boardResults = new ArrayList<>();
    for (Board board: boards) {
      boardResults.add(new BoardResult(board));
    }

    ApiResult apiResult = ApiResult.blank()
      .add("user", userData)
      .add("teams", teamResults)
      .add("boards", boardResults)
      .add("settings", settings);

    System.out.println("user:" + userData + "\nteams: " + teamResults + "\nboards: " + boardResults);
    return Result.ok(apiResult);
  }

  /**
   * Team Result
   */
  private static class TeamResult {
    private final long id;
    private final String name;

    TeamResult(Team team) {
      this.id = team.getId().value();
      this.name = team.getName();
    }

    public long getId() {
      return id;
    }

    public String getName() {
      return name;
    }
  }

  /**
   * Board Result
   */
  private static class BoardResult {
    private final long id;
    private final String name;
    private final String description;
    private final long teamId;

    BoardResult(Board board) {
      this.id = board.getId().value();
      this.name = board.getName();
      this.description = board.getDescription();
      this.teamId = board.getTeamId().value();
    }

    public long getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public String getDescription() {
      return description;
    }

    public long getTeamId() {
      return teamId;
    }
  }
}
