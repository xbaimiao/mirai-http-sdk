package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonArray
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.AtComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.message.component.elements.AtTarget

class AtComponentImpl(
    override val target: AtTarget,
    override val display: String,
    children: List<BaseComponent> = ComponentList()
) : AtComponent, AbstractComponent(children) {

    override fun fromChildren(children: List<BaseComponent>): BaseComponent = AtComponentImpl(target, display, children)

    override fun serializeToJson(): JsonArray {
        TODO("Not yet implemented")
    }

    override fun serializeToPlainText(): String {
        TODO("Not yet implemented")
    }
}
