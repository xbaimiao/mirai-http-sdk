package com.xbaimiao.mirai.message.serialize.component.impl

import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.ImageComponent
import com.xbaimiao.mirai.message.component.TextComponent
import com.xbaimiao.mirai.message.serialize.component.ComponentPlainTextSerializer

object ComponentPlainTextSerializerImpl : ComponentPlainTextSerializer {

    override fun serialize(value: BaseComponent) =
        value.toList().joinToString(separator = "", transform = ComponentPlainTextSerializerImpl::asText)

    private fun asText(component: BaseComponent) = when (component) {
        is TextComponent -> component.string
        is ImageComponent -> "[图片]"
        else -> ""
    }
}