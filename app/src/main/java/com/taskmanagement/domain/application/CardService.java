package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.*;
import com.taskmanagement.domain.model.activity.Activity;
import com.taskmanagement.domain.model.attachment.Attachment;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.card.Card;
import com.taskmanagement.domain.model.card.CardId;

import java.util.List;

public interface CardService {
  /**
   * 보드에 속한 모든 카드 찾기
   * @param boardId 보드 아이디
   * @return 카드 정보
   */
  List<Card> findByBoardId(BoardId boardId);

  /**
   * Id로 카드 찾기
   * @param cardId 카드 아이디
   * @return 카드
   */
  Card findById(CardId cardId);

  /**
   * 카드Id 활동 이력 조회
   * @param cardId 카드 아이디
   * @return 활동 이력
   */
  List<Activity> findCardActivities(CardId cardId);

  /**
   * 카드의 첨부 파일 찾기
   * @param cardId 카드 아이디
   * @return 첩부 파일 정보
   */
  List<Attachment> getAttachments(CardId cardId);

  /**
   * 카드 추가하기
   * @param command 카드 추가하기 명령
   * @return 카드 정보
   */
  Card addCard(AddCardCommand command);

  /**
   * 카드 위치 변경
   * @param command 카드 위치 변경 명령
   */
  void changePositions(ChangeCardPositionsCommand command);

  /**
   * 카드 제목 변경
   * @param command 카드 제목 변경 명령
   */
  void changeCardTitle(ChangeCardTitleCommand command);

  /**
   * 카드 설명 변경
   * @param command 카드 설명 변경 명령
   */
  void changeCardDescription(ChangeCardDescriptionCommand command);

  /**
   * 카드에 댓글 추가하기
   * @param command 카드에 댓글 추가 명령
   * @return 활동 이력
   */
  Activity addComment(AddCardCommentCommand command);

  /**
   * 카드에 첨부파일 추가하기
   * @param command 카드에 첨부파일 추가 명령
   * @return 파일 첨부
   */
  Attachment addAttachment(AddCardAttachmentCommand command);
}
