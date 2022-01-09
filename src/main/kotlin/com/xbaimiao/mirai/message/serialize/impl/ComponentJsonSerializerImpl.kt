package com.xbaimiao.mirai.message.serialize.impl

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.ImageComponent
import com.xbaimiao.mirai.message.component.TextComponent
import com.xbaimiao.mirai.message.serialize.ComponentJsonSerializer

object ComponentJsonSerializerImpl : ComponentJsonSerializer {

    override fun serialize(value: BaseComponent): JsonObject {
        val jsonArray = JsonArray()
        value.toList().map { it.toJson() }.forEach(jsonArray::add)
        return jsonArray.asJsonObject
    }

    override fun deserialize(value: BaseComponent): JsonObject {
        TODO()
    }

    private fun BaseComponent.toJson() = when (this) {
        is TextComponent -> JsonObject().apply {
            addProperty("type", "Plain")
            addProperty("text", this@toJson.string)
        }
        is ImageComponent -> JsonObject().apply {
            addProperty("type", "Image")
            addProperty("url", this@toJson.url.toString())
        }
        else -> JsonObject()
    }
}
