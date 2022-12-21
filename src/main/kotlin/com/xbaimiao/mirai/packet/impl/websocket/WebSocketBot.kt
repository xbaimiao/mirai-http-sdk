package com.xbaimiao.mirai.packet.impl.websocket

import com.xbaimiao.mirai.entity.Friend
import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.eventbus.EventChancel
import com.xbaimiao.mirai.packet.enums.EntityType
import com.xbaimiao.mirai.packet.impl.group.BotProfilePacket
import com.xbaimiao.mirai.packet.impl.group.EntityListPacket
import com.xbaimiao.mirai.packet.impl.group.QQProfile
import java.net.ConnectException
import java.net.URI
import java.util.concurrent.CompletableFuture

/**
 * 绑定WebSocket 监听事件
 */
class WebSocketBot(config: WsInfo) {

    companion object {
        lateinit var bot: WebSocketBot
        private lateinit var hook: Thread

        init {
            hook = object : Thread() {

                override fun run() {
                    Runtime.getRuntime().removeShutdownHook(hook)
                    //程序关闭 自动安全断线
                    bot.disable()
                }

            }
            hook.name = "MiraiHttpSDK-ShutdownHook-" + Thread.activeCount()
            Runtime.getRuntime().addShutdownHook(hook)
        }
    }

    fun getGroups(): CompletableFuture<List<Group>> {
        return CompletableFuture<List<Group>>().apply {
            EntityListPacket<Group>(EntityType.GROUP).send().thenAcceptAsync {
                groupCache = it.entitys
                this.complete(it.entitys)
            }
        }
    }

    fun profile(): CompletableFuture<QQProfile?> {
        val future = CompletableFuture<QQProfile?>()
        BotProfilePacket().send().thenApplyAsync {
            future.complete(it.botProfile)
        }
        return future
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
    internal lateinit var webSocket: WSListener

    private var safeShutdown = false
    private val url = config.baseUrl.replace("http", "ws") + "all?verifyKey=${config.authKey}&qq=${id}"
    var groupCache = arrayListOf<Group>()
    var friendCache = arrayListOf<Friend>()

    fun connect(): WebSocketBot {
        try {
            webSocket = WSListener(bot, URI(url))
            webSocket.connectBlocking()
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
        webSocket.close(1000, "close")
        webSocket.on = false
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
            Thread.currentThread().join()
        }.start()
    }

    init {
        bot = this
    }

}