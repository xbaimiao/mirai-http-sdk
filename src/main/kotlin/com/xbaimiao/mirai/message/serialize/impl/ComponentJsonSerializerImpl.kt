package com.xbaimiao.mirai.message.serialize.impl

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.message.component.ImageComponent
import com.xbaimiao.mirai.message.component.TextComponent
import com.xbaimiao.mirai.message.serialize.component.ComponentJsonSerializer
import java.net.URL

object ComponentJsonSerializerImpl : ComponentJsonSerializer {

    override fun serialize(value: BaseComponent): JsonArray {
        val jsonArray = JsonArray()
        value.toList().map { it.toJson() }.forEach(jsonArray::add)
        return jsonArray
    }

    override fun deserialize(value: JsonArray): BaseComponent {
        var component: BaseComponent = Component.text("")
        value.forEach {
            when (it.asJsonObject.get("type").asString) {
                "Source" -> {}
                "Plain" -> component += Component.text(it.asJsonObject.get("text").asString)
                "Image" -> component += Component.image(URL(it.asJsonObject.get("url").asString))
            }
        }
        return component
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
