package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.AddCardListCommand;
import com.taskmanagement.domain.application.commands.ChangeCardListPositionsCommand;
import com.taskmanagement.domain.application.commands.DeleteCardListCommand;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.cardlist.CardList;

import java.util.List;

public interface CardListService {
  /**
   * 보드의 카드 리스트 찾기
   * @param boardId 보드 아이디
   * @return 카드 리스트
   */
  List<CardList> findByBoardId(BoardId boardId);

  /**
   * 카드 리스트 추가
   * @param command 카드 리스트 추가 명령
   * @return 카드 리스트
   */
  CardList addCardList(AddCardListCommand command);

  /**
   * 카드 리스트 위치 변경
   * @param command 카드 위치 변경 명령
   */
  void changePositions(ChangeCardListPositionsCommand command);

  void deleteCardList(DeleteCardListCommand command);
}
