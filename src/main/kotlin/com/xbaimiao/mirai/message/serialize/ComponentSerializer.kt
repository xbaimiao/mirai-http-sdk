package com.xbaimiao.mirai.message.serialize

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.ImageComponent
import com.xbaimiao.mirai.message.component.TextComponent

sealed interface ComponentSerializer {

    object Json :
        MiraiSerializer<BaseComponent, JsonObject>,
        MiraiDeserializer<BaseComponent, JsonObject>,
        ComponentSerializer {

        override fun serialize(value: BaseComponent): JsonObject {
            val jsonArray = JsonArray()
            value.toList()
                .map { it.toJson() }
                .forEach(jsonArray::add)
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

    object PlainText : MiraiSerializer<BaseComponent, String>, ComponentSerializer {

        override fun serialize(value: BaseComponent) =
            value.toList().joinToString(transform = ::asText)

        private fun asText(component: BaseComponent) = when (component) {
            is TextComponent -> component.string
            is ImageComponent -> "[图片]"
            else -> ""
        }
    }
}
