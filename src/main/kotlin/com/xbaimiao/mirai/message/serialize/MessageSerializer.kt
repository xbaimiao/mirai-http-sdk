@file:Suppress("unused")

package com.xbaimiao.mirai.message.serialize

import com.google.gson.JsonObject
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.message.Message

sealed interface MessageSerializer {

    object Json : MiraiSerializer<Message, JsonObject>, MiraiDeserializer<JsonObject, Message>, MessageSerializer {

        override fun serialize(value: Message): JsonObject {
            val jsonObject = JsonObject()
            if (value.target is MemberFriend) {
                jsonObject.addProperty("qq", value.target.id)
                jsonObject.addProperty("group", (value.target as MemberFriend).group.id)
            } else {
                jsonObject.addProperty("target", value.target.id)
            }
            jsonObject.add("messageChain", ComponentSerializer.Json.serialize(value.component))
            return jsonObject
        }

        override fun deserialize(value: JsonObject): Message {
            TODO()
        }

    }

    object PlainText : MiraiSerializer<Message, String>, MessageSerializer {

        override fun serialize(value: Message): String {
            TODO()
        }

    }

}
