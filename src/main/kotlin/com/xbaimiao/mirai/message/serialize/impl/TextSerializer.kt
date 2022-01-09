package com.xbaimiao.mirai.message.serialize.impl

import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.ImageComponent
import com.xbaimiao.mirai.message.component.TextComponent
import com.xbaimiao.mirai.message.serialize.MessageSerializer

object TextSerializer : MessageSerializer<BaseComponent, String> {

    override fun serialize(input: BaseComponent): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(input.toText())
        input.children.forEach { stringBuilder.append(it.toText()) }
        return stringBuilder.toString()
    }

    private fun BaseComponent.toText() = when (this) {
        is TextComponent -> this.string
        is ImageComponent -> "[图片]"
        else -> ""
    }

}