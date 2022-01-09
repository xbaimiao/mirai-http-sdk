package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.packet.CommandPacket
import com.xbaimiao.mirai.packet.SyncIdPool
import java.io.StringReader
import java.util.concurrent.CompletableFuture

class GroupListPacket : CommandPacket<GroupListPacket>(
    SyncIdPool.next(), "groupList", null, JsonObject()
) {

    val groups = ArrayList<Group>()

    override fun put(json: String): GroupListPacket {
        val jsonObject = JsonParser.parseReader(StringReader(json)).asJsonObject
        jsonObject.get("data").asJsonObject.get("data").asJsonArray.forEach {
            it.asJsonObject.apply {
                groups.add(Gson().fromJson(this.toString(), Group::class.java))
            }
        }
        future.complete(this)
        return this
    }

    override val future = CompletableFuture<GroupListPacket>()

}