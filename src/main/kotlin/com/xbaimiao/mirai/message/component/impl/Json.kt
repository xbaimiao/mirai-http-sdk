package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.packet.enums.ComponentType

class Json(
    val json: String,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    constructor(json: String) : this(json, ComponentList())

    override fun fromChildren(children: List<BaseComponent>) = Json(json, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", "Json")
            addProperty("json", json)
        }
    }

    override fun serializeToPlainText(): String {
        return "[JSON]"
    }

}
