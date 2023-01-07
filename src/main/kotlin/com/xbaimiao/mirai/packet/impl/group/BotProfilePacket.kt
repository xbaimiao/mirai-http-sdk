package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.xbaimiao.mirai.packet.CommandPacket
import java.io.StringReader

class BotProfilePacket(
) : CommandPacket<BotProfilePacket>(
    command = "botProfile",
    subCommand = "get",
    content = JsonObject().apply {
    }
) {

    var botProfile: QQProfile? = null

    override fun put(json: String): BotProfilePacket {
        Gson().fromJson(
            JsonParser.parseReader(StringReader(json)).asJsonObject.getAsJsonObject("data"),
            QQProfile::class.java
        )?.let {
            botProfile = it
        }
        future.complete(this)
        return this
    }

}