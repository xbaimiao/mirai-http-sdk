package com.xbaimiao.mirai.packet.impl

import com.xbaimiao.mirai.packet.enums.HttpMethod
import com.xbaimiao.mirai.packet.Packet

class BindPacket(
    val qq: Long,
    val sessionKey: String
) : Packet() {

    override val httpMethod = HttpMethod.POST
    override val targetedPath = "bind"

    override fun <T : Packet> read(): T {
        TODO("Not yet implemented")
    }

}