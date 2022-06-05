package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.xbaimiao.mirai.packet.CommandPacket
import java.io.StringReader

class UnMuteMemberFriendPacket(
    val memberId: Long,
    val target: Long
) : CommandPacket<UnMuteMemberFriendPacket>(
    command = "unmute",
    content = JsonObject().apply {
        addProperty("memberId", memberId)
        addProperty("target", target)
    }
) {

    lateinit var result: Result

    override fun put(json: String): UnMuteMemberFriendPacket {
        val jsonObject = JsonParser.parseReader(StringReader(json)).asJsonObject.getAsJsonObject("data")
        result = when (jsonObject.get("code").asInt) {
            0 -> Result.SUCCESS
            5 -> Result.NOT_MEMBER
            else -> Result.FAILED
        }
        future.complete(this)
        return this
    }

    enum class Result {
        SUCCESS,
        FAILED,
        NOT_MEMBER,
    }

}