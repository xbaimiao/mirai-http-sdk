package com.xbaimiao.mirai.packet.impl

import com.google.gson.JsonParser
import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.GroupImpl
import com.xbaimiao.mirai.packet.Packet
import com.xbaimiao.mirai.packet.enums.HttpMethod
import java.io.StringReader

class GroupListPacket(session: String) : Packet() {

    val groups = ArrayList<Group>()

    override val httpMethod: HttpMethod = HttpMethod.GET

    override val targetedPath: String = "groupList?sessionKey=${session}"

    @Suppress("UNCHECKED_CAST")
    override fun <T : Packet> read(): T {
        this.send {
            val body = this.body()
            val jsonObject = JsonParser.parseReader(StringReader(body)).asJsonObject
            if (jsonObject.get("code").asInt != 0) {
                return@send
            }
            jsonObject.get("data").asJsonArray.forEach {
                it.asJsonObject.apply {
                    groups.add(
                        GroupImpl(
                            this.get("name").asString,
                            this.get("id").asLong,
                            Group.BotPermission.valueOf(this.get("permission").asString)
                        )
                    )
                }
            }
        }
        return this as T
    }

}