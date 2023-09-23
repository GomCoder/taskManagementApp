package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.AddCardCommand;
import com.taskmanagement.domain.application.commands.ChangeCardPositionsCommand;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.card.Card;

import java.util.List;

public interface CardService {
  /**
   * 보드에 속한 모든 카드 찾기
   * @param boardId
   * @return
   */
  List<Card> findByBoardId(BoardId boardId);

  /**
   * 카드 추가하기
   * @param command
   * @return
   */
  Card addCard(AddCardCommand command);

  /**
   * 카드 위치 변경
   * @param command
   */
  void changePositions(ChangeCardPositionsCommand command);
}
