package com.taskmanagement.web.results;

import com.taskmanagement.domain.model.board.Board;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.domain.model.cardlist.CardList;
import com.taskmanagement.domain.model.cardlist.CardListId;
import com.taskmanagement.domain.model.team.Team;
import com.taskmanagement.domain.model.user.User;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardResult {
  public static ResponseEntity<ApiResult> build(Team team,
                                                Board board,
                                                List<User> members,
                                                List<CardList> cardLists,
                                                List<Card> cards) {
    Map<String, Object> boardData = new HashMap<>();
    boardData.put("id", board.getId().value());
    boardData.put("name", board.getName());
    boardData.put("personal", board.isPersonal());

    List<MemberData> membersData = new ArrayList<>();

    for(User user: members) {
      membersData.add(new MemberData(user));
    }

    List<CardListData> cardListsData  = new ArrayList<>();
    Map<CardListId, List<Card>> cardsByList = new HashMap<>();

    for(Card card: cards) {
      cardsByList.computeIfAbsent(card.getCardListId(), k -> new ArrayList<>()).add(card);
    }

    for(CardList cardList: cardLists) {
      cardListsData.add(new CardListData(cardList, cardsByList.get(cardList.getId())));
    }

    ApiResult result = ApiResult.blank()
      .add("board", boardData)
      .add("members", membersData)
      .add("cardLists", cardListsData);

    if (!board.isPersonal()) {
      Map<String, String> teamData = new HashMap<>();
      teamData.put("name", team.getName());
      result.add("team", teamData);
    }
    return Result.ok(result);
  }

  private static class MemberData {
    private final long userId;
    private final String shortName;

    MemberData(User user) {
      this.userId = user.getId().value();
      this.shortName = user.getInitials();
    }

    public long getUserId() {
      return userId;
    }

    public String getShortName() {
      return shortName;
    }
  }

  private static class CardListData {
    private final long id;
    private final String name;
    private final int position;
    private final List<CardData> cards = new ArrayList<>();

    CardListData(CardList cardList, List<Card> cards) {
      this.id = cardList.getId().value();
      this.name = cardList.getName();
      this.position = cardList.getPosition();

      if (cards != null) {
        for(Card card: cards) {
          this.cards.add(new CardData(card));
        }
      }
    }

    public long getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public int getPosition() {
      return position;
    }

    public List<CardData> getCards() {
      return cards;
    }
  }

  private static class CardData {
    private final long id;
    private final String title;
    private final int position;

    CardData(Card card) {
      this.id = card.getId().value();
      this.title = card.getTitle();
      this.position = card.getPosition();
    }

    public long getId() {
      return id;
    }

    public String getTitle() {
      return title;
    }

    public int getPosition() {
      return position;
    }
  }
}
