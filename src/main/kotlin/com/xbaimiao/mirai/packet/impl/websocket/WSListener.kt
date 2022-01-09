package com.xbaimiao.mirai.packet.impl.websocket

import com.google.gson.JsonParser
import com.xbaimiao.mirai.MiraiHttpSDK
import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.enums.Permission
import com.xbaimiao.mirai.event.group.GroupMessageEvent
import com.xbaimiao.mirai.eventbus.EventManger
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.packet.CommandPacket
import java.io.StringReader
import java.net.http.WebSocket
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

/**
 * 监听器
 */
class WSListener(private val webSocketBot: WebSocketBot) : WebSocket.Listener {

    val putPackets = HashMap<Long, CommandPacket<*>>()
    var buffer = StringBuilder()
    private val accumulatedMessage = CompletableFuture<Any>()

    override fun onOpen(webSocket: WebSocket) {
        webSocket.request(1)
    }

    override fun onClose(webSocket: WebSocket?, statusCode: Int, reason: String?): CompletionStage<*>? {
        return null
    }

    override fun onError(webSocket: WebSocket, error: Throwable) {
        error.printStackTrace()
        MiraiHttpSDK.init()
    }

    override fun onText(webSocket: WebSocket, charSequence: CharSequence, last: Boolean): CompletionStage<*> {
        buffer.append(charSequence)
        webSocket.request(1)
        if (!last) {
            return accumulatedMessage
        }
        val text = buffer.toString()
        buffer = StringBuilder()
        try {
            val jsonObject = JsonParser.parseReader(StringReader(text)).asJsonObject
            if (!jsonObject.has("syncId")) {
                return CompletableFuture<Any>()
            }
            val syncId = jsonObject.get("syncId").asString
            if (syncId == "-1") {
                val data = jsonObject.getAsJsonObject("data")
                when (data.get("type").asString) {
                    "GroupMessage" -> {

                    }
                }
//                EventManger.call(GroupMessageEvent(Group("1", 1, Permission.MEMBER), Component.text("1")))
                //肯定是消息
                return accumulatedMessage
            }
            if (syncId == "") {
                //推送消息
                return accumulatedMessage
            }
            putPackets.keys.forEach {
                if (syncId.toLong() == it) {
                    putPackets[it]!!.put(jsonObject.toString())
                }
            }
            putPackets.remove(syncId.toLong())
        } catch (e: Exception) {
            println("input \"$text\"")
            e.printStackTrace()
        }
        return accumulatedMessage
    }

}