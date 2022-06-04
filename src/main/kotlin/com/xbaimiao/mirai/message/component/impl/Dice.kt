package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList

class Dice(
    val value: Int,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    constructor(value: Int) : this(value, ComponentList())

    override fun fromChildren(children: List<BaseComponent>) = Dice(value, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", "Dice")
            addProperty("value", value)
        }
    }

    override fun serializeToPlainText(): String {
        return "[Dice]"
    }

}
