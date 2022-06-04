package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.packet.enums.ComponentType

class Poke(
    val name: PokeType,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    constructor(name: PokeType) : this(name, ComponentList())

    override fun fromChildren(children: List<BaseComponent>) = Poke(name, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", "Poke")
            addProperty("name", PokeType.TypeToString(name))
        }
    }

    override fun serializeToPlainText(): String {
        return PokeType.TypeToString(name)
    }

}
