package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.JsonObject
import com.xbaimiao.mirai.event.NewFriendRequestEvent
import com.xbaimiao.mirai.packet.CommandPacket
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot

class FriendRequestResponsePacket(
    val event: NewFriendRequestEvent,
    private val response: String,
    private val operate: Operate
) : CommandPacket<FriendRequestResponsePacket>(
    command = "resp_newFriendRequestEvent",
    content = JsonObject().apply {
        addProperty("sessionKey", WebSocketBot.bot.getSessionKey())
        addProperty("eventId", event.eventId)
        addProperty("fromId", event.fromId)
        addProperty("groupId", event.groupId)
        addProperty("operate", operate.getCode())
        addProperty("message", response)
    }
) {

    override fun put(json: String): FriendRequestResponsePacket {
        future.complete(this)
        return this
    }

    enum class Operate(private val code: Int) {
        ACCEPT(0),
        DENY(1),
        DENY_AND_BLOCK(2);

        fun getCode(): Int {
            return code
        }
    }
}