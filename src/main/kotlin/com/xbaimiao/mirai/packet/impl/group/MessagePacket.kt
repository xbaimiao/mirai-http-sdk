package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.xbaimiao.mirai.packet.CommandPacket
import com.xbaimiao.mirai.packet.enums.MessageType
import java.io.StringReader
import java.util.concurrent.CompletableFuture

class MessagePacket(json: JsonObject, type: MessageType) : CommandPacket<MessagePacket>(
    command = type.command,
    content = json
) {

    var messageId: Int = -9999

    override fun put(json: String): MessagePacket {
        val jsonObject = JsonParser.parseReader(StringReader(json)).asJsonObject.getAsJsonObject("data")
        println(json)
        if (jsonObject.get("msg").asString == "success") {
            messageId = jsonObject.get("messageId").asInt
        }
        future.complete(this)
        return this
    }

}