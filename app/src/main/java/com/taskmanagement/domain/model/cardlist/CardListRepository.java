package com.taskmanagement.domain.model.cardlist;

import com.taskmanagement.domain.model.board.BoardId;

import java.util.List;

/** 
 * 카드 리스트 레포지토리 - 보드 아이디로 카드 리스트 조회, 카드 리스트 저장, 
 *                        카드 리스트 위치 변경, 카드 리스트 아이디로 카드 리스트 조회, 
 *                        카드 리스트 삭제
 */
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

  /**
   * Id로 카드 리스트 찾기
   * @param cardListId
   * @return
   */
  CardList findById(CardListId cardListId);

  /**
   * 카드 리스트 삭제
   */
  void deleteCardList(CardListId cardListId);
}
