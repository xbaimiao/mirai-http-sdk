package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.packet.CommandPacket
import java.io.StringReader

class GetMemberInfoPacket(
    val target: Long,
    val memberId: Long
) : CommandPacket<GetMemberInfoPacket>(
    command = "memberInfo",
    subCommand = "get",
    content = JsonObject().apply {
        addProperty("target", target)
        addProperty("memberId", memberId)
    }
) {

    var memberFriend: MemberFriend? = null

    override fun put(json: String): GetMemberInfoPacket {
        Gson().fromJson(
            JsonParser.parseReader(StringReader(json)).asJsonObject.getAsJsonObject("data"),
            MemberFriend::class.java
        )?.let {
            memberFriend = it
        }
        future.complete(this)
        return this
    }

}