package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.packet.enums.ComponentType

class Emoji(
    val emojiId: Int,
    val name: String = "bu",
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    override fun fromChildren(children: List<BaseComponent>): BaseComponent =
        Emoji(emojiId, name, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", ComponentType.FACE.key)
            addProperty("faceId", emojiId)
            addProperty("name", name)
        }
    }

    override fun serializeToPlainText(): String {
        return "/$name"
    }
}
