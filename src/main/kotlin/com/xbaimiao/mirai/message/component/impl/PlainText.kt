package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.packet.enums.ComponentType

class PlainText(
    val string: String,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    constructor(string: String) : this(string, ComponentList())

    override fun fromChildren(children: List<BaseComponent>) = PlainText(string, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", ComponentType.PLAIN.key)
            addProperty("text", string)
        }
    }

    override fun serializeToPlainText(): String {
        return string
    }

}
