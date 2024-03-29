package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.xbaimiao.mirai.packet.CommandPacket
import java.io.StringReader

class MemberProfilePacket(
    val target: Long,
    val memberId: Long
) : CommandPacket<MemberProfilePacket>(
    command = "memberProfile",
    subCommand = "get",
    content = JsonObject().apply {
        addProperty("target", target)
        addProperty("memberId", memberId)
    }
) {

    var memberProfile: QQProfile? = null

    override fun put(json: String): MemberProfilePacket {
        Gson().fromJson(
            JsonParser.parseReader(StringReader(json)).asJsonObject.getAsJsonObject("data"),
            QQProfile::class.java
        )?.let {
            memberProfile = it
        }
        future.complete(this)
        return this
    }

}