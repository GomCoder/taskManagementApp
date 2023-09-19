<script>
import PageHeader from '@/components/PageHeader.vue'
import draggable from 'vuedraggable'
import $ from 'jquery'
import AddMemberModal from '@/modals/AddMemberModal.vue'
import notify from '@/utils/notify'
import boardService from '@/services/boards'
import cardListService from '@/services/card-lists'
import cardService from '@/services/cards'
export default {
  name: 'BoardPage',
  data () {
    return {
      board: {
        id: 0,
        name: '',
        personal: false
      },
      /* {id, name, cards, cardForm} */
      cardLists: [],
      team: {
        name: ''
      },
      /* {id, shortName} */
      members: [],
      addListForm: {
        open: false,
        name: ''
      }
    }
  },
  components: {
    PageHeader,
    AddMemberModal,
    draggable
  },
  beforeRouteEnter (to, from, next) {
    next(vm => {
      vm.loadBoard()
    })
  },
  beforeRouteUpdate (to, from, next) {
    next()
    this.unsubscribeFromRealTimeUpadate()
    this.loadBoard()
  },
  beforeRouteLeave (to, from, next) {
    next()
    this.unsubscribeFromRealTimeUpadate()
  },
  mounted () {
    this.$el.addEventListener('click', this.dismissActiveForms)
  },
  beforeDestroy () {
    this.$el.removeEventListener('click', this.dismissActiveForms)
  },
  methods: {
    loadBoard () {
      console.log('[BoardPage] Loading board')
      boardService.getBoard(this.$route.params.boardId).then(data => {
        this.team.name = data.team ? data.team.name : ''
        this.board.id = data.board.id
        this.board.personal = data.board.personal
        this.board.name = data.board.name
        this.members.splice(0)
        data.members.forEach(member => {
          this.members.push({
            id: member.userId,
            shortName: member.shortName
          })
        })
        this.cardLists.splice(0)
        data.cardLists.sort((list1, list2) => {
          return list1.position - list2.position
        })
        data.cardLists.forEach(cardList => {
          cardList.cards.sort((card1, card2) => {
            return card1.position - card2.position
          })
          this.cardLists.push({
            id: cardList.id,
            name: cardList.name,
            cards: cardList.cards,
            cardForm: {
              open: false,
              title: ''
            }
          })
        })
        this.subscribeToRealTimeUpdate()
      }).catch(error => {
        notify.error(error.message)
      })
    },
    dismissActiveForms (event) {
      console.log('[BoardPage] Dismissing forms')
      let dismissAddCardForm = true
      let dismissAddListForm = true
      if (event.target.closeset('.add-card-form') || event.target.closeset('.add-card-button')) {
        dismissAddCardForm = false
      }
      if (event.target.closeset('.add-list-form') || event.target.closeset('.add-list-button')) {
        dismissAddListForm = false
      }
      if (dismissAddCardForm) {
        this.cardLists.forEach((cardList) => {
          cardList.cardForm.open = false
        })
      }
      if (dismissAddListForm) {
        this.addListForm.open = false
      }
    },
    openAddMember () {
      $('#addMemberModal').modal('show')
    },
    openMemberAdded (member) {
      this.members.push(member)
    },
    addCardList () {
      if (!this.addListForm.name) {
        return
      }
      const cardList = {
        boardId: this.board.id,
        name: this.addListForm.name,
        position: this.cardLists.length + 1
      }
      cardListService.add(cardList).then(savedCardList => {
        this.cardLists.push({
          id: savedCardList.id,
          name: savedCardList.name,
          cards: [],
          cardForm: {
            open: false,
            title: ''
          }
        })
        this.closeAddListForm()
      }).catch(error => {
        notify.error(error.message)
      })
    },
    addCard (cardList) {
      if (!cardList.cardForm.title.trim()) {
        return
      }
      const card = {
        boardId: this.board.id,
        cardListId: cardList.id,
        title: cardList.cardForm.title,
        position: cardList.cards.length + 1
      }
      cardService.add(card).then(savedCard => {
        this.appendCardToList(cardList, savedCard)
        cardList.cardForm.title = ''
        this.focusCardForm(cardList)
      }).catch(error => {
        notify.error(error.message)
      })
    },
    openAddListForm () {
      this.addListForm.open = true
      this.$nextTick(() => {
        $('#cardListName').trigger('focus')
      })
    },
    closeAddListForm () {
      this.addListForm.open = false
      this.addListForm.name = ''
    },
    openAddCardForm (cardList) {
      this.cardLists.forEach((cardList) => {
        cardList.cardForm.open = false
      })
      cardList.cardForm.open = true
      this.focusCardForm(cardList)
    },
    focusCardForm (cardList) {
      this.$nextTick(() => {
        $('#cardTitle' + cardList.id).trigger('focus')
      })
    },
    closeAddCardForm (cardList) {
      cardList.cardForm.open = false
    },
    onCardListDragEnded (event) {
      console.log('[BoardPage] Card list drag ended.', event)
      const positionChanges = {
        boardId: this.board.id,
        cardListPositions: []
      }
      this.cardLists.forEach((cardList, index) => {
        positionChanges.cardListPositions.push({
          cardListId: cardList.id,
          position: index + 1
        })
      })
      cardListService.changePositions(positionChanges).catch(error => {
        notify.error(error.message)
      })
    },
    onCardDragEnded (event) {
      console.log('[BoardPage] Card drag ended', event)
      const fromListId = event.from.dataset.cardListId
      const toListId = event.to.dataset.cardListId
      const changedListIds = [fromListId]

      if (fromListId !== toListId) {
        changedListIds.push(toListId)
      }
      const positionChanges = {
        boardId: this.board.id,
        cardPositions: []
      }

      changedListIds.forEach(cardListId => {
        const cardList = this.cardLists.filter(cardList => {
          return cardList.id === parseInt(cardListId)
        })[0]

        cardList.cards.forEach((card, index) => {
          positionChanges.cardPositions.push({
            cardListId: cardListId,
            cardId: card.id,
            position: index + 1
          })
        })
      })
      cardService.changePositions(positionChanges).catch(error => {
        notify.error(error.message)
      })
    },
    subscribeToRealTimeUpdate () {
      this.$rt.subscribe('/board/' + this.board.id, this.onRealTimeUpdated)
    },
    unsubscribeFromRealTimeUpdate () {
      this.$rt.unsubscribe('/board/' + this.board.id, this.onRealTimeUpdated)
    },
    onRealTimeUpdated (update) {
      console.log('[BoardPage] Real Time update recived', update)
      if (update.type === 'cardAdded') {
        this.onCardAdded(update.card)
      }
    },
    onCardAdded (card) {
      const cardList = this.cardLists.filter(cardList => {
        return cardList.id === card.cardListId
      })[0]

      if (!cardList) {
        console.log('No card list found by id ' + card.cardListId)
        return
      }
      this.appendCardToList(cardList, card)
    },
    appendCardToList (cardList, card) {
      const exstingIndex = cardList.cards.findIndex(exstingCard => {
        return exstingCard.id === card.id
      })
      if (exstingIndex === -1) {
        cardList.cards.push({
          id: card.id,
          title: card.title
        })
      }
    }
  }
}
</script>

<template>
<div class="page">
  <PageHeader />
  <div class="page-body">
    <div class="board-wrapper">
      <div class="board">
        <div class="board-header clearfix">
          <div class="board-name board-header-item">{{ board.name }}</div>
          <div class="board-header-divider"></div>
          <div class="team-name board-header-item">
            <span v-if="!board.personal">{{ team.name }}</span>
            <span v-if="board.personal">Personal</span>
          </div>
          <div class="board-header-divider"></div>
          <div class="board-members board-header-item">
            <div class="member" v-for="member in members" v-bind:key="member.id">
              <span>{{ member.shortName }}</span>
            </div>
            <div class="member add-member-toggle" @click="openAddMember()">
              <span><font-awesome-icon icon="user-plus" /></span>
            </div>
          </div>
        </div>
        <div class="board-body">
          <draggable v-model="cardLists" class="list-container" @end="onCardListDragEnded"
          :options="{handle: '.list-header', animation: 0, scrollSensitivity: 100, touchStartThreshold: 20}">
            <div class="list-wrapper" v-for="cardList in cardLists" v-bind:key="cardList.id">
              <div class="list">
                <div class="list-header">{{ cardList.name }}</div>
                <draggable class="cards" v-model="cardList.cards" @end="onCardDragEnded"
                :options="{draggable: '.card-item', group: 'cards', ghostClass: 'ghost-card', animation: 0, scrollSensivity: 100, touchStartThreshold: 20}" v-bind:data-list-id="cardList.id">
                  <div class="card-item" v-for="card in cardList.cards" v-bind:key="card.id">
                    <div class="card-title">{{ card.title }}</div>
                  </div>
                  <div class="add-card-form-wrapper" v-if="cardList.cardForm.open">
                    <form @submit.prevent="addCard(cardList)" class="add-card-form">
                      <div class="form-group">
                        <textarea class="form-control" v-model="cardList.cardForm.title" v-bind:id="'cardTitle' + cardList.id" @keydown.enter.prevent="addCard(cardList)" placeholder="Type card title here"></textarea>
                      </div>
                      <button type="submit" class="btn btn-sm btn-primary">Add</button>
                      <button type="button" class="btn btn-sm btn-link btn-cancel" @click="closeAddCardForm(cardList)">Cancel</button>
                    </form>
                  </div>
                </draggable>
                <div class="add-card-button" v-show="!cardList.cardForm.open" @click="openAddCardForm(cardList)">+ Add a card</div>
              </div>
            </div>
            <div class="list-wrapper add-list">
              <div class="add-list-button" v-show="!addListForm.open" @click="openAddListForm()">+ Add a list</div>
              <form @submit.prevent="addCardList()" v-show="addListForm.open" class="add-list-form">
                <div class="form-group">
                  <input type="text" class="form-control" v-model="addListForm.name" id="cardListName" placeholder="Type list name here" />
                </div>
                <button type="submit" class="btn btn-sm btn-primary">Add List</button>
                <button type="button" class="btn btn-sm btn-link btn-cancel" @click="closeAddListForm()">Cancel</button>
              </form>
            </div>
          </draggable>
        </div>
      </div>
    </div>
  </div>
  <AddMemberModal :boardId="board.id" @added="onMemberAdded" />
</div>
</template>

<style scoped lang="scss">
.page-body {
}

</style>
