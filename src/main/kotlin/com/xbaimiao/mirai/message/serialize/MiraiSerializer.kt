package com.xbaimiao.mirai.message.serialize

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.impl.*
import com.xbaimiao.mirai.packet.enums.ComponentType

sealed interface MiraiSerializer<I, O> {
    fun serialize(value: I): O
    fun deserialize(value: O): I

    object MessageSerialize : MiraiSerializer<Message, JsonObject> {

        override fun deserialize(value: JsonObject): Message {
            TODO("Not yet implemented")
        }

        override fun serialize(value: Message): JsonObject {
            val jsonObject = JsonObject()
            if (value.target is MemberFriend) {
                jsonObject.addProperty("qq", value.target.id)
                jsonObject.addProperty("group", (value.target as MemberFriend).group.id)
            } else {
                jsonObject.addProperty("target", value.target.id)
            }
            val jsonArray = JsonArray()
            value.component.toList().map { it.serializeToJson() }.forEach(jsonArray::add)
            jsonObject.add("messageChain", jsonArray)
            return jsonObject
        }

    }

    object ComponentSerializer : MiraiSerializer<BaseComponent, JsonArray> {
        override fun serialize(value: BaseComponent): JsonArray {
            TODO("Not yet implemented")
        }

        override fun deserialize(value: JsonArray): BaseComponent {
            var component: BaseComponent = PlainText("")
            value.map { it.asJsonObject }.forEach {
                when (ComponentType.formKey(it.get("type").asString)) {
                    ComponentType.SOURCE -> {}
                    ComponentType.PLAIN -> component += PlainText(it.get("text").asString)
                    ComponentType.IMAGE -> component += Image(it)
                    ComponentType.AT -> component += At(it.get("target").asLong, it.get("display").asString)
                    ComponentType.FACE -> component += Emoji(it.get("faceId").asInt, it.get("name").asString)
                    ComponentType.NULL -> {}
                    ComponentType.AT_ALL -> component += AtAll()
                    ComponentType.FLASH_IMAGE -> TODO()
                    ComponentType.VOICE -> TODO()
                }
            }
            return component
        }
    }

    object ComponentText : MiraiSerializer<BaseComponent, String> {
        override fun deserialize(value: String): BaseComponent {
            TODO("Not yet implemented")
        }

        override fun serialize(value: BaseComponent): String =
            value.toList().joinToString(separator = "", transform = { it.serializeToPlainText() })
    }

}
