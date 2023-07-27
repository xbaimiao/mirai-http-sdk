package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.JsonObject
import com.xbaimiao.mirai.packet.CommandPacket

class MuteAllPacket(
    val groupId: Long,
    val unmute: Boolean = false
) : CommandPacket<MuteAllPacket>(
    command = if (unmute) "unmuteAll" else "muteAll",
    content = JsonObject().apply {
        addProperty("target", groupId)
    }
) {
    override fun put(json: String): MuteAllPacket {
        future.complete(this)
        return this
    }

}