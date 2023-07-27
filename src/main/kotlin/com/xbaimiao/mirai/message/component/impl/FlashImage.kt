package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList

class FlashImage(
    val imageId: String,
    val url: String,
    val base64: String,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    constructor(imageId: String, url: String, base64: String) : this(imageId, url, base64, ComponentList())

    override fun fromChildren(children: List<BaseComponent>) = FlashImage(imageId, url, base64, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", "FlashImage")
            if (imageId == "") {
                add("imageId", JsonNull.INSTANCE)
            } else {
                addProperty("imageId", imageId)
            }
            if (url == "") {
                add("url", JsonNull.INSTANCE)
            } else {
                addProperty("url", url)
            }
            if (base64 == "") {
                add("base64", JsonNull.INSTANCE)
            } else {
                addProperty("base64", base64)
            }
        }
    }

    override fun serializeToPlainText(): String {
        return "[闪照]"
    }

}
