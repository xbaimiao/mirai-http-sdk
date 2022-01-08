package com.xbaimiao.mirai.message.component.impl

import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.TextComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList

class TextComponentImpl(
    override val string: String,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children), TextComponent {

    override fun fromChildren(children: List<BaseComponent>) = TextComponentImpl(string, children)
}
