package com.xbaimiao.mirai.packet.impl.websocket

import com.xbaimiao.mirai.entity.Friend
import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.eventbus.EventChancel
import com.xbaimiao.mirai.packet.enums.EntityType
import com.xbaimiao.mirai.packet.impl.group.EntityListPacket
import java.net.ConnectException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.concurrent.CompletableFuture

/**
 * 绑定WebSocket 监听事件
 */
class WebSocketBot(config: WsInfo) {

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

    val eventChancel = EventChancel
    val id = config.qq

    internal val closeFunc = ArrayList<WebSocketBot.() -> Unit>()
    internal lateinit var webSocket: WebSocket
    internal lateinit var wsListener: WSListener

    private var safeShutdown = false
    private var httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(6, ChronoUnit.SECONDS)).build()
    private val url = config.baseUrl.replace("http", "ws") + "all?verifyKey=${config.authKey}&qq=${id}"
    var groupCache = arrayListOf<Group>()
    var friendCache = arrayListOf<Friend>()

    fun connect(): WebSocketBot {
        try {
            webSocket =
                httpClient.newWebSocketBuilder().buildAsync(URI(url), WSListener(this).also { wsListener = it }).join()
            getGroups()
            getFriends()
        } catch (e: ConnectException) {
            e.printStackTrace()
        }
        return this
    }

    /**
     * 安全关闭机器人 此方法关闭不会调用自动重新连接的方法
     */
    fun disable() {
        safeShutdown = true
        webSocket.sendClose(1000, "close")
        webSocket.abort()
        wsListener.on = false
    }

    /**
     * 自动重新连接
     */
    fun autoReconnect(delay: Int) {
        onClose {
            Thread {
                if (safeShutdown) {
                    return@Thread
                }
                println("Bot closed $delay seconds later will reconnect")
                Thread.sleep(delay * 1000L)
                connect()
                println("Bot Connected!")
            }.start()
        }
    }

    /**
     * 关闭事件
     */
    fun onClose(func: WebSocketBot.() -> Unit) {
        closeFunc.add(func)
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