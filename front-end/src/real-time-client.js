import Vue from 'vue'
import SockJS from 'sockjs-client'
import globalBus from '@/event-bus'

// 실시간 통신을 위한 설정
class RealTimeClient {
  // 생성자
  constructor () {
    this.serverUrl = null
    this.token = null
    this.socket = null
    this.authenticated = false
    this.loggedOut = false
    this.triedAttemps = 0
    this.$bus = new Vue()
    this.subscribeQueue = {
      /* channel: [handler1, handler2] */
    }
    this.unsubscribeQueue = {
      /* channel: [handler1, handler2] */
    }
  }

  // 커넥션을 초기화
  init (serverUrl, token) {
    if (this.authenticated) {
      console.warn('[RealTimeClient] WS connection already authenticated.')
      return
    }
    console.log('[RealTimeClient] Initializing')
    this.serverUrl = serverUrl
    this.token = token
    this.connect()
  }

  // 실시간 클라이언트의 프로퍼티를 초기 값으로 재설정하고 소켓을 닫아 클라이언트의 상태를 제거함
  logout () {
    console.log('[RealTimeClient] Logging out')
    this.subscribeQueue = {}
    this.unsubscribeQueue = {}
    this.authenticated = false
    this.loggedOut = true
    this.socket && this.socket.close()
  }

  // 실시간 클라이언트와 서버가 연결
  connect () {
    console.log('[RealTimeClient] Connecting to ' + this.serverUrl)
    this.socket = new SockJS(this.serverUrl + '?token=' + this.token)
    this.socket.onopen = () => {
      this.authenticated = true
      this._onConnected()
    }
    this.socket.onmessage = (event) => {
      this._onMessageReceived(event)
    }
    this.socket.onerror = (error) => {
      this._onSocketError(error)
    }
    this.socket.onclose = (event) => {
      this._onClosed(event)
    }
  }

  // 실시간 클라이언트가 자신이 관심있는 채널을 구독하기 위한 API
  subscribe (channel, handler) {
    if (!this._isConnected()) {
      this._addToSubscribeQueue(channel, handler)
      return
    }
    const message = {
      action: 'subscribe',
      channel
    }
    this._send(message)
    this.$bus.$on(this._channelEvent(channel), handler)
    console.log('[RealTimeClient] Subscribed to channel ' + channel)
  }

  unsubscribe (channel, handler) {
    if (this.loggedOut) {
      return
    }
    if (!this._isConnected()) {
      this._addToUnsubscribeQueue(channel, handler)
      return
    }
    const message = {
      action: 'unsubscribe',
      channel
    }
    this._send(message)
    this.$bus.$off(this._channelEvent(channel), handler)
    console.log('[RealTimeClient] Unsubscribed from channel ' + channel)
  }

  _isConnected () {
    return this.socket && this.socket.readyState === SockJS.OPEN
  }

  _onConnected () {
    this.triedAttemps = 0
    globalBus.$emit('RealTimeClient.connected')
    console.log('[RealTimeClient] Connected')

    this._processQueues()
  }

  _onMessageReceived (event) {
    const message = JSON.parse(event.data)
    console.log('[RealTimeClient] Received message', message)
    if (message.channel) {
      this.$bus.$emit(this._channelEvent(message.channel), JSON.parse(message.payload))
    }
  }

  _send (message) {
    this.socket.send(JSON.stringify(message))
  }

  _onSocketError (error) {
    console.log('[RealTimeClient] Socket error', error)
  }

  _onClosed (event) {
    console.log('[RealTimeClient] Received close event', event)
    if (this.loggedOut) {
      console.log('[RealTimeClient] Logged out')
      globalBus.$emit('RealTimeClient.loggedOut')
    } else {
      if (this.triedAttemps > 30) {
        console.log('[RealTimeClient] Fail to connect to the server')
        return
      }
      console.log('[RealTimeClient] Disconnected')
      globalBus.$emit('RealTimeClient.disconnected')

      setTimeout(() => {
        this.triedAttemps++
        console.log('[RealTimeClient] Reconnecting')
        globalBus.$emit('RealTimeClient.reconnecting')
        this.connect()
      }, 1000)
    }
  }

  _channelEvent (channel) {
    return 'channel:' + channel
  }

  _processQueues () {
    console.log('[RealTimeClient] Processing subscribe/unsubscribe queues')
    const subscribeChannels = Object.keys(this.subscribeQueue)
    subscribeChannels.forEach(channel => {
      const handlers = this.subscribeQueue[channel]
      handlers.forEach(handler => {
        this.subscribe(channel, handler)
        this._removeFromQueue(this.subscribeQueue, channel, handler)
      })
    })
    const unsubscribeChannels = Object.keys(this.unsubscribeQueue)
    unsubscribeChannels.forEach(channel => {
      const handlers = this.unsubscribeQueue[channel]
      handlers.forEach(handler => {
        this.unsubscribe(channel, handler)
        this._removeFromQueue(this.unsubscribeQueue, channel, handler)
      })
    })
  }

  _addToSubscribeQueue (channel, handler) {
    console.log('[RealTimeClient] Adding channel subscribe to queue. Channel: ' + channel)
    this._removeFromQueue(this.unsubscribeQueue, channel, handler)
    const handlers = this.subscribeQueue[channel]
    if (!handlers) {
      this.subscribeQueue[channel] = [handler]
    } else {
      handlers.push(handlers)
    }
  }

  _addToUnsubscribeQueue (channel, handler) {
    console.log('[RealTimeClient] Adding channel unsubscribe to queue. Channel: ' + channel)
    this._removeFromQueue(this.subscribeQueue, channel, handler)
    const handlers = this.unsubscribeQueue[channel]
    if (!handlers) {
      this.unsubscribeQueue[channel] = [handler]
    } else {
      handlers.push(handlers)
    }
  }

  _removeFromQueue (queue, channel, handler) {
    const handlers = queue[channel]
    if (handlers) {
      const index = handlers.indexOf(handler)
      if (index > -1) {
        handlers.splice(index, 1)
      }
    }
  }
}

export default new RealTimeClient()
