package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.packet.enums.ComponentType

class MiraiCode(
    val code: String,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    constructor(code: String) : this(code, ComponentList())

    override fun fromChildren(children: List<BaseComponent>) = MiraiCode(code, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", "MiraiCode")
            addProperty("code", code)
        }
    }

    override fun serializeToPlainText(): String {
        return code
    }

}
