package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.packet.CommandPacket
import java.io.StringReader

class FriendProfilePacket(
    val target: Long
) : CommandPacket<FriendProfilePacket>(
    command = "friendProfile",
    subCommand = "get",
    content = JsonObject().apply {
        addProperty("target", target)
    }
) {

    var memberProfile: QQProfile? = null

    override fun put(json: String): FriendProfilePacket {
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