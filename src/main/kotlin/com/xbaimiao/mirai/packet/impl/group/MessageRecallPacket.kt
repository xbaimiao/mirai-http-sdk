package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.xbaimiao.mirai.packet.CommandPacket
import java.io.StringReader

class MessageRecallPacket(
    val target: Long,
    val messageId: Long
) : CommandPacket<MessageRecallPacket>(
    command = "recall",
    content = JsonObject().apply {
        addProperty("target", target)
        addProperty("messageId", messageId)
    }
) {

    lateinit var result: Result

    override fun put(json: String): MessageRecallPacket {
        val jsonObject = JsonParser.parseReader(StringReader(json)).asJsonObject.getAsJsonObject("data")
        result = when (jsonObject.get("code").asInt) {
            0 -> Result.SUCCESS
            else -> Result.FAILED
        }
        future.complete(this)
        return this
    }

    enum class Result {
        SUCCESS,
        FAILED
    }

}