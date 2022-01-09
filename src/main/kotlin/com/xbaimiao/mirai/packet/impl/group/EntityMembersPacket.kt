package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.packet.CommandPacket
import com.xbaimiao.mirai.packet.enums.EntityType
import java.io.StringReader
import java.util.concurrent.CompletableFuture

class EntityMembersPacket(
    val group: Group
) : CommandPacket<EntityMembersPacket>(
    command = EntityType.GROUP_FRIEND_LIST.command,
    content = JsonObject().apply {
        this.addProperty("target", group.id)
    }
) {

    val friends = ArrayList<MemberFriend>()

    override fun put(json: String): EntityMembersPacket {
        val jsonObject = JsonParser.parseReader(StringReader(json)).asJsonObject.getAsJsonObject("data")
        jsonObject.getAsJsonArray("data").forEach {
            friends.add(Gson().fromJson(it.asJsonObject, MemberFriend::class.java))
        }
        future.complete(this)
        return this
    }

}