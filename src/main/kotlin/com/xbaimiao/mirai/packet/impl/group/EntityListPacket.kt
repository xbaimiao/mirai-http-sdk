package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.xbaimiao.mirai.packet.CommandPacket
import com.xbaimiao.mirai.packet.enums.EntityType
import java.io.StringReader
import java.util.concurrent.CompletableFuture

/**
 * 用于获取群列表 或者 好友列表
 */
open class EntityListPacket<V>(val entityType: EntityType) :
    CommandPacket<EntityListPacket<V>>(command = entityType.command) {

    val entitys = ArrayList<V>()

    @Suppress("Unchecked_cast")
    override fun put(json: String): EntityListPacket<V> {
        val jsonObject = JsonParser.parseReader(StringReader(json)).asJsonObject
        jsonObject.get("data").asJsonObject.get("data").asJsonArray.forEach {
            it.asJsonObject.apply {
                entitys.add(Gson().fromJson(this.toString(), entityType.clazz) as V)
            }
        }
        future.complete(this)
        return this
    }

}