package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.JsonObject
import com.xbaimiao.mirai.packet.CommandPacket
import com.xbaimiao.mirai.packet.SyncIdPool
import com.xbaimiao.mirai.packet.enums.MessageType
import java.util.concurrent.CompletableFuture

class MessagePacket(json: JsonObject, type: MessageType) : CommandPacket<MessagePacket>(
    SyncIdPool.next(), "sendGroupMessage", null, json
) {

    override val future = CompletableFuture<MessagePacket>()

    override fun put(json: String): MessagePacket {
        future.complete(this)
        return this
    }

}