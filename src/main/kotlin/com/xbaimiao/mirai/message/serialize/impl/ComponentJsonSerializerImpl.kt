package com.xbaimiao.mirai.message.serialize.impl

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.*
import com.xbaimiao.mirai.message.serialize.component.ComponentJsonSerializer
import com.xbaimiao.mirai.packet.enums.ComponentType
import com.xbaimiao.mirai.packet.enums.ComponentType.*
import java.net.URL

object ComponentJsonSerializerImpl : ComponentJsonSerializer {

    override fun serialize(value: BaseComponent): JsonArray {
        val jsonArray = JsonArray()
        value.toList().map { it.toJson() }.forEach(jsonArray::add)
        return jsonArray
    }

    override fun deserialize(value: JsonArray): BaseComponent {
        var component: BaseComponent = Component.text("")
        value.map { it.asJsonObject }.forEach {
            when (ComponentType.formKey(it.get("type").asString)) {
                SOURCE -> {}
                PLAIN -> component += Component.text(it.get("text").asString)
                IMAGE -> component += Component.image(URL(it.get("url").asString))
                AT -> component += Component.at(it.get("target").asLong, it.get("display").asString)
                FACE -> component += Component.face(it.get("faceId").asInt, it.get("name").asString)
                NULL -> {}
                AT_ALL -> component += Component.atAll()
                FLASH_IMAGE -> TODO()
                VOICE -> TODO()
            }
        }
        return component
    }

    private fun BaseComponent.toJson() = when (this) {
        // 文本消息
        is TextComponent -> JsonObject().apply {
            addProperty("type", PLAIN.key)
            addProperty("text", this@toJson.string)
        }
        // 图片消息
        is ImageComponent -> JsonObject().apply {
            addProperty("type", IMAGE.key)
            addProperty("url", this@toJson.url.toString())
        }
        // 艾特消息
        is AtComponent -> JsonObject().apply {
            addProperty("type", AT.key)
            addProperty("target", this@toJson.target)
            addProperty("display", this@toJson.display)
        }
        // 艾特全体
        is AtAllComponent -> JsonObject().apply {
            addProperty("type", AT_ALL.key)
        }
        // emoji
        is FaceComponent -> JsonObject().apply {
            addProperty("type", FACE.key)
            addProperty("faceId", this@toJson.faceId)
            addProperty("name", this@toJson.name)
        }
        else -> JsonObject()
    }
}
