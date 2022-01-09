package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonArray
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.EmojiComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList

class EmojiComponentImpl(
    override val emojiId: Int,
    override val name: String,
    children: List<BaseComponent> = ComponentList()
) : EmojiComponent, AbstractComponent(children) {

    override fun fromChildren(children: List<BaseComponent>): BaseComponent =
        EmojiComponentImpl(emojiId, name, children)

    override fun serializeToJson(): JsonArray {
        TODO("Not yet implemented")
    }

    override fun serializeToPlainText(): String {
        TODO("Not yet implemented")
    }
}
