package com.taskmanagement.domain.model.card;

import com.taskmanagement.domain.model.board.BoardId;

import java.util.List;

public interface CardRepository {
  /**
   * 보드의 카드들 찾기
   * @param boardId
   * @return
   */
  List<Card> findByBoardId(BoardId boardId);

  /**
   * 카드 저장하기
   * @param card
   */
  void save(Card card);
  /**
   * 카드 위치 변경
   */
  void changePositions(List<CardPosition> cardPositions);
}
