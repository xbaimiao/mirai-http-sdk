package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.packet.enums.ComponentType

class At(
    val target: Long,
    val display: String,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    override fun fromChildren(children: List<BaseComponent>): BaseComponent = At(target, display, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", ComponentType.AT.key)
            addProperty("target", target)
            addProperty("display", display)
        }
    }

    override fun serializeToPlainText(): String {
        return "@${target}"
    }

}

class AtAll(children: List<BaseComponent> = ComponentList()) : AbstractComponent(children) {

    override fun fromChildren(children: List<BaseComponent>): BaseComponent = AtAll(children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", ComponentType.AT_ALL.key)
        }
    }

    override fun serializeToPlainText(): String {
        return "@全体成员"
    }

}