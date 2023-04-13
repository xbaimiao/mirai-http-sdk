package com.xbaimiao.mirai.message.component.impl

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.packet.enums.ComponentType

class ForwardMessage(
    val nodeList: List<ForwardNode>,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    constructor(nodeList: List<ForwardNode>) : this(nodeList, ComponentList())

    override fun fromChildren(children: List<BaseComponent>): BaseComponent =
        ForwardMessage(nodeList, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", ComponentType.FORWARD_MESSAGE.key)
            add("nodeList", Gson().toJsonTree(nodeList))
        }
    }

    override fun serializeToPlainText(): String {
        return "[转发]"
    }
}
