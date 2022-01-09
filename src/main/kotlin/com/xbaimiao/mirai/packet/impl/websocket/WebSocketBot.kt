package com.xbaimiao.mirai.packet.impl.websocket

import com.xbaimiao.mirai.config.WebSocketBotConfig
import com.xbaimiao.mirai.entity.Friend
import com.xbaimiao.mirai.entity.Group
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

    fun getGroups(): CompletableFuture<List<Group>> {
        return CompletableFuture<List<Group>>().apply {
            EntityListPacket<Group>(EntityType.GROUP).send().thenAcceptAsync {
                this.complete(it.entitys)
            }
        }
    }

    fun getFriends(): CompletableFuture<List<Friend>> {
        return CompletableFuture<List<Friend>>().apply {
            EntityListPacket<Friend>(EntityType.FRIEND).send().thenAcceptAsync {
                this.complete(it.entitys)
            }
        }
    }

    private var httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(6, ChronoUnit.SECONDS)).build()
    lateinit var webSocket: WebSocket
    lateinit var wsListener: WSListener

    private val url = config.baseUrl.replace("http", "ws") + "all?verifyKey=${config.authKey}&qq=${config.qq}"

    fun connect(): WebSocketBot {
        webSocket =
            httpClient.newWebSocketBuilder().buildAsync(URI(url), WSListener(this).also { wsListener = it }).join()
        return this
    }

    fun join() {
        Thread {
            while (true) {
                Thread.sleep(10000)
            }
        }.start()
    }

}