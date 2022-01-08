package com.xbaimiao.mirai.packet.impl

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.xbaimiao.mirai.entity.group.Group
import com.xbaimiao.mirai.packet.Packet
import com.xbaimiao.mirai.packet.enums.HttpMethod
import java.io.StringReader

class GroupListPacket(session: String) : Packet() {

    val groups = ArrayList<com.xbaimiao.mirai.entity.MiraiMessageTransmittable>()

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
                    groups.add(Gson().fromJson(this.toString(), Group::class.java))
                }
            }
        }
        return this as T
    }

}