package com.xbaimiao.mirai.message.serialize.impl

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.ImageComponent
import com.xbaimiao.mirai.message.component.TextComponent
import com.xbaimiao.mirai.message.serialize.MessageDeserializer
import com.xbaimiao.mirai.message.serialize.MessageSerializer

object ComponentSerializer : MessageDeserializer<BaseComponent, JsonArray>,
    MessageSerializer<BaseComponent, JsonArray> {
    override fun deserializer(input: JsonArray): BaseComponent {
        TODO("Not yet implemented")
    }

    override fun serialize(input: BaseComponent): JsonArray {
        val jsonArray = JsonArray()
        jsonArray.add(input.toJson())
        input.children
            .map { it.toJson() }
            .forEach(jsonArray::add)
        return jsonArray
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