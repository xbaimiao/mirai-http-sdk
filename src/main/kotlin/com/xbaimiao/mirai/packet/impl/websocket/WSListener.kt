package com.xbaimiao.mirai.packet.impl.websocket

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.xbaimiao.mirai.entity.Friend
import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.event.*
import com.xbaimiao.mirai.eventbus.EventChancel
import com.xbaimiao.mirai.message.MessageSource
import com.xbaimiao.mirai.message.serialize.MiraiSerializer
import com.xbaimiao.mirai.packet.CommandPacket
import java.io.StringReader
import java.net.http.WebSocket
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

/**
 * 监听器
 */
class WSListener(private val bot: WebSocketBot) : WebSocket.Listener {

    val putPackets = HashMap<Long, CommandPacket<*>>()
    var buffer = StringBuilder()
    private val accumulatedMessage = CompletableFuture<Any>()
    internal var on = true

    override fun onOpen(webSocket: WebSocket) {
        webSocket.request(1)
    }

    override fun onClose(webSocket: WebSocket?, statusCode: Int, reason: String?): CompletionStage<*>? {
        bot.closeFunc.forEach { it.invoke(bot) }
        return null
    }

    override fun onError(webSocket: WebSocket, error: Throwable) {
        error.printStackTrace()
    }

    override fun onText(webSocket: WebSocket, charSequence: CharSequence, last: Boolean): CompletionStage<*> {
        if (!on) {
            return accumulatedMessage
        }
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
                //消息
                val data = jsonObject.getAsJsonObject("data")
                when (data.get("type").asString) {
                    "GroupMessage" -> {
                        val memberFriend =
                            Gson().fromJson(data.get("sender").asJsonObject, MemberFriend::class.java)
                        val groupMessageEvent = GroupMessageEvent(
                            memberFriend.group,
                            memberFriend,
                            data.get("messageChain").asJsonArray.toSource(),
                            MiraiSerializer.ComponentSerializer.deserialize(data.get("messageChain").asJsonArray)
                        )
                        EventChancel.call(groupMessageEvent)
                    }
                    "TempMessage" -> {
                        val memberFriend =
                            Gson().fromJson(data.get("sender").asJsonObject, MemberFriend::class.java)
                        val groupMessageEvent = GroupTempMessageEvent(
                            memberFriend.group,
                            memberFriend,
                            data.get("messageChain").asJsonArray.toSource(),
                            MiraiSerializer.ComponentSerializer.deserialize(data.get("messageChain").asJsonArray)
                        )
                        EventChancel.call(groupMessageEvent)
                    }
                    "FriendMessage" -> {
                        val friend = Gson().fromJson(data.get("sender").asJsonObject, Friend::class.java)
                        val friendMessageEvent = FriendMessageEvent(
                            friend,
                            data.get("messageChain").asJsonArray.toSource(),
                            MiraiSerializer.ComponentSerializer.deserialize(data.get("messageChain").asJsonArray)
                        )
                        EventChancel.call(friendMessageEvent)
                    }
                    "BotGroupPermissionChangeEvent" -> {
                        val groupPermissionEvent = GroupPermissionChangeEvent(
                            Gson().fromJson(data.get("group").asJsonObject, Group::class.java),
                            data.get("origin").asString,
                            data.get("current").asString
                        )
                        EventChancel.call(groupPermissionEvent)
                    }
                }
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

    private fun JsonArray.toSource(): MessageSource {
        for (it in this) {
            if (it.asJsonObject.get("type").asString == "Source") {
                return Gson().fromJson(it, MessageSource::class.java)
            }
        }
        return MessageSource(-1, -1)
    }

}