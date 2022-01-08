package com.xbaimiao.mirai.packet.impl.group

import com.xbaimiao.mirai.packet.Packet
import com.xbaimiao.mirai.packet.enums.HttpMethod

class GroupMessagePacket(
    private val json: String
) : Packet() {

    override val httpMethod = HttpMethod.POST
    override val targetedPath = "sendGroupMessage"

    var messageId: Int = -999
    var code: Int = -999

    override fun toJson(): String {
        return json
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Packet> read(): T {
        return this as T
    }

}