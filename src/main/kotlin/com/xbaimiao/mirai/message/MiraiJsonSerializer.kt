package com.xbaimiao.mirai.message

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.xbaimiao.mirai.entity.group.Group
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.message.component.ImageComponent
import com.xbaimiao.mirai.message.component.TextComponent

class MiraiJsonSerializer(val group: Group, val message: Component) {

    private val jsonObject = JsonObject()

    fun serializer(): String {
        jsonObject.addProperty("sessionKey", group.sessionKey)
        jsonObject.addProperty("target", group.id)
        val jsonArray = JsonArray()
        for (component in message.getComponents()) {
            val jsonObject = when (component) {
                is TextComponent -> JsonObject().apply {
                    this.addProperty("type", "Plain")
                    this.addProperty("text", component.string)
                }
                is ImageComponent -> JsonObject().apply {
                    this.addProperty("type", "Image")
                    this.addProperty("url", component.url)
                }
                else -> JsonObject()
            }
            jsonArray.add(jsonObject)
        }
        jsonObject.add("messageChain", jsonArray)
        return jsonObject.toString()
    }
}