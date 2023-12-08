package com.taskmanagement.domain.application.impl;

import com.taskmanagement.domain.application.CardListService;
import com.taskmanagement.domain.application.commands.AddCardListCommand;
import com.taskmanagement.domain.application.commands.ChangeCardListPositionsCommand;
import com.taskmanagement.domain.application.commands.DeleteCardListCommand;
import com.taskmanagement.domain.common.event.DomainEventPublisher;
import com.taskmanagement.domain.model.board.BoardId;
import com.taskmanagement.domain.model.cardlist.CardList;
import com.taskmanagement.domain.model.cardlist.CardListRepository;
import com.taskmanagement.domain.model.cardlist.events.CardListAddedEvent;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 카드 리스트 서비스 구현
 */
@Service
@Transactional
public class CardListServiceImpl implements CardListService {
  private final CardListRepository cardListRepository;
  private final DomainEventPublisher domainEventPublisher;

  public CardListServiceImpl(CardListRepository cardListRepository,
                             DomainEventPublisher domainEventPublisher) {
    this.cardListRepository = cardListRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  /**
   * 보드 아이디로 보드에 속한 카드 리스트 조회
   */
  @Override
  public List<CardList> findByBoardId(BoardId boardId) {
    return cardListRepository.findByBoardId(boardId);
  }

  /**
   * 카드 리스트 추가
   */
  @Override
  public CardList addCardList(AddCardListCommand command) {
    CardList cardList = CardList.create(command.getBoardId(),
                                        command.getUserId(),
                                        command.getName(),
                                        command.getPosition());
    cardListRepository.save(cardList);
    domainEventPublisher.publish(new CardListAddedEvent(cardList, command));
    System.out.println(cardList.getCreatedDate());
    return cardList;
  }

  /**
   * 카드 리스트의 위치 변경 정보 조회
   */
  @Override
  public void changePositions(ChangeCardListPositionsCommand command) {
    cardListRepository.changePositions(command.getCardListPositions());
  }

  /**
   * 카드 리스트 삭제
   */
  @Override
  public void deleteCardList(DeleteCardListCommand command) {
    cardListRepository.deleteCardList(command.getCardListId());
  }
}
