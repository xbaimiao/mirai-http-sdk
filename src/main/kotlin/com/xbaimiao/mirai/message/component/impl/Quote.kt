package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.packet.enums.ComponentType

class Quote(
    val id: Int,
    val groupId: Long,
    val senderId: Long,
    val targetId: Long,
    val origin: JsonArray,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    constructor(id: Int, groupId: Long, senderId: Long, targetId: Long, origin: JsonArray) : this(
        id,
        groupId,
        senderId,
        targetId,
        origin,
        ComponentList()
    )

    override fun fromChildren(children: List<BaseComponent>) = Quote(id, groupId, senderId, targetId, origin, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", ComponentType.QUOTE.key)
            addProperty("id", id)
            addProperty("groupId", groupId)
            addProperty("senderId", senderId)
            addProperty("targetId", targetId)
            add("origin", origin)
        }
    }

    override fun serializeToPlainText(): String {
        return "[引用]";
    }

}
