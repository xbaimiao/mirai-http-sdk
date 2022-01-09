@file:Suppress("unused")

package com.xbaimiao.mirai.message.serialize

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.ImageComponent
import com.xbaimiao.mirai.message.component.TextComponent

sealed interface MessageSerializer<S, D> {
    fun serialize(input: S): D

    object Json : MessageSerializer<Message, JsonObject> {

        override fun serialize(input: Message): JsonObject {
            val jsonObject = JsonObject()

            jsonObject.addProperty("target", input.target.id)
            val jsonArray = JsonArray()
            jsonArray.add(input.component.toJson())
            input.component.children
                .map { it.toJson() }
                .forEach(jsonArray::add)

            jsonObject.add("messageChain", jsonArray)
            return jsonObject
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
}
