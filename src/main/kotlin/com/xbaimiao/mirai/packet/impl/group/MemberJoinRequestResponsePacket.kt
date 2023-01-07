package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.JsonObject
import com.xbaimiao.mirai.event.MemberJoinRequestEvent
import com.xbaimiao.mirai.packet.CommandPacket
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot

class MemberJoinRequestResponsePacket(
    val event: MemberJoinRequestEvent,
    private val response: String,
    private val operate: Operate
) : CommandPacket<MemberJoinRequestResponsePacket>(
    command = "resp_memberJoinRequestEvent",
    content = JsonObject().apply {
        addProperty("sessionKey", WebSocketBot.bot.getSessionKey())
        addProperty("eventId", event.eventId)
        addProperty("fromId", event.fromId)
        addProperty("groupId", event.groupId)
        addProperty("operate", operate.getCode())
        addProperty("message", response)
    }
) {

    override fun put(json: String): MemberJoinRequestResponsePacket {
        future.complete(this)
        return this
    }

    enum class Operate(private val code: Int) {
        ACCEPT(0),
        DENY(1),
        IGNORE(2),
        DENY_AND_BLOCK(3),
        IGNORE_AND_BLOCK(4);

        fun getCode(): Int {
            return code
        }
    }
}