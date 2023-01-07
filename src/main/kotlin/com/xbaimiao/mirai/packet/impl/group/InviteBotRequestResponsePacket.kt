package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.JsonObject
import com.xbaimiao.mirai.event.BotInvitedJoinGroupRequestEvent
import com.xbaimiao.mirai.packet.CommandPacket
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot

class InviteBotRequestResponsePacket(
    val event: BotInvitedJoinGroupRequestEvent,
    private val response: String,
    private val operate: Operate
) : CommandPacket<InviteBotRequestResponsePacket>(
    command = "resp_botInvitedJoinGroupRequestEvent",
    content = JsonObject().apply {
        addProperty("sessionKey", WebSocketBot.bot.getSessionKey())
        addProperty("eventId", event.eventId)
        addProperty("fromId", event.fromId)
        addProperty("groupId", event.groupId)
        addProperty("operate", operate.getCode())
        addProperty("message", response)
    }
) {

    override fun put(json: String): InviteBotRequestResponsePacket {
        future.complete(this)
        return this
    }

    enum class Operate(private val code: Int) {
        ACCEPT(0),
        DENY(1);

        fun getCode(): Int {
            return code
        }
    }
}