package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.xbaimiao.mirai.packet.CommandPacket
import java.io.StringReader

class MuteMemberFriendPacket(
    val memberId: Long,
    val target: Long,
    val time: Int
) : CommandPacket<MuteMemberFriendPacket>(
    command = "mute",
    content = JsonObject().apply {
        addProperty("memberId", memberId)
        addProperty("target", target)
        addProperty("time", time)
    }
) {

    lateinit var result: Result

    override fun put(json: String): MuteMemberFriendPacket {
        val jsonObject = JsonParser.parseReader(StringReader(json)).asJsonObject.getAsJsonObject("data")
        result = when (jsonObject.get("code").asInt) {
            0 -> {
                Result.SUCCESS
            }
            5 -> {
                Result.NOT_MEMBER
            }
            else -> {
                Result.FAILED
            }
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