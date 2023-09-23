package com.taskmanagement.domain.model.cardlist;

import com.taskmanagement.domain.model.board.BoardId;

import java.util.List;

public interface CardListRepository {
  /**
   * 보드의 카드 리스트 찾기
   * @param boardId
   * @return
   */
  List<CardList> findByBoardId(BoardId boardId);
  /**
   * 카드 리스트 저장하기
   */
  void save(CardList cardList);

  /**
   * 카드 리스트 위치 변경
   * @param cardListPositions
   */
  void changePositions(List<CardListPosition> cardListPositions);
}
