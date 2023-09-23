package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.AddCardListCommand;
import com.taskmanagement.domain.application.commands.ChangeCardListPositionsCommand;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.cardlist.CardList;

import java.util.List;

public interface CardListService {
  /**
   * 보드의 카드 리스트 찾기
   * @param boardId
   * @return
   */
  List<CardList> findByBoardId(BoardId boardId);

  /**
   * 카드 리스트 추가
   * @param command
   * @return
   */
  CardList addCardList(AddCardListCommand command);

  /**
   * 카드 리스트 위치 변경
   * @param command
   */
  void changePositions(ChangeCardListPositionsCommand command);
}
