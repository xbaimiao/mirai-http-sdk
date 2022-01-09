package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonArray
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.ImageComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import java.net.URL

class ImageComponentImpl(
    override val url: URL,
    children: List<BaseComponent> = ComponentList()
) : ImageComponent, AbstractComponent(children) {

    override fun fromChildren(children: List<BaseComponent>) = ImageComponentImpl(url, children)

    override fun serializeToJson(): JsonArray {
        TODO("Not yet implemented")
    }

    override fun serializeToPlainText(): String {
        TODO("Not yet implemented")
    }
}
