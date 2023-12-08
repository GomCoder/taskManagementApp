package com.taskmanagement.domain.model.card;

import com.taskmanagement.domain.model.board.BoardId;

import java.util.List;

/**
 * 카드 레포지토리 - 보드 아이디로 보드에 속한 카드 조회, 카드 저장, 카드 위치 변경, 카드 아이디로 카드 찾기, 
 *                  카드 삭제
 */
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

  /**
   * Id로 카드 찾기
   * @param cardId
   * @return
   */
  Card findById(CardId cardId);

  /**
   * 카드 삭제
   */
  Card deleteCard(CardId cardId);
}
