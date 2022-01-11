package com.xbaimiao.mirai.packet.impl.websocket

import com.xbaimiao.mirai.config.WebSocketBotConfig
import com.xbaimiao.mirai.entity.Friend
import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.eventbus.EventChancel
import com.xbaimiao.mirai.packet.enums.EntityType
import com.xbaimiao.mirai.packet.impl.group.EntityListPacket
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.concurrent.CompletableFuture

/**
 * 绑定WebSocket 监听事件
 */
class WebSocketBot(config: WebSocketBotConfig) {

    companion object {
        lateinit var bot: WebSocketBot
    }

    fun getGroups(): CompletableFuture<List<Group>> {
        return CompletableFuture<List<Group>>().apply {
            EntityListPacket<Group>(EntityType.GROUP).send().thenAcceptAsync {
                groupCache = it.entitys
                this.complete(it.entitys)
            }
        }
    }

    fun getFriends(): CompletableFuture<List<Friend>> {
        return CompletableFuture<List<Friend>>().apply {
            EntityListPacket<Friend>(EntityType.FRIEND).send().thenAcceptAsync {
                friendCache = it.entitys
                this.complete(it.entitys)
            }
        }
    }

    fun getFriend(target: Long): Friend? {
        return friendCache.firstOrNull { it.id == target }
    }

    fun getGroup(id: Long): Group? {
        return groupCache.firstOrNull { it.id == id }
    }

    var groupCache = arrayListOf<Group>()
    var friendCache = arrayListOf<Friend>()
    val eventChancel = EventChancel
    val id = config.qq
    lateinit var webSocket: WebSocket
    lateinit var wsListener: WSListener

    private var httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(6, ChronoUnit.SECONDS)).build()
    private val url = config.baseUrl.replace("http", "ws") + "all?verifyKey=${config.authKey}&qq=${config.qq}"

    fun connect(): WebSocketBot {
        webSocket =
            httpClient.newWebSocketBuilder().buildAsync(URI(url), WSListener().also { wsListener = it }).join()
        return this
    }

    fun disable() {
        webSocket.sendClose(1, "close")
    }

    fun join() {
        Thread {
            while (true) {
                Thread.sleep(10000)
            }
        }.start()
    }

    init {
        bot = this
    }

}