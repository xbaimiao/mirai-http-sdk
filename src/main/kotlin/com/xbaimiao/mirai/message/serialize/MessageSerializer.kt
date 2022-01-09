@file:Suppress("unused")

package com.xbaimiao.mirai.message.serialize

import com.google.gson.JsonObject
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.serialize.impl.ComponentSerializer

interface MessageDeserializer<S, D> {
    fun deserializer(input: D): S
}

interface MessageSerializer<S, D> {
    fun serialize(input: S): D

    object Json : MessageSerializer<Message, JsonObject> {

        override fun serialize(input: Message): JsonObject {
            val jsonObject = JsonObject()
            if (input.target is MemberFriend) {
                jsonObject.addProperty("qq", input.target.id)
                jsonObject.addProperty("group", (input.target as MemberFriend).group.id)
            } else {
                jsonObject.addProperty("target", input.target.id)
            }
            jsonObject.add("messageChain", ComponentSerializer.serialize(input.component))
            return jsonObject
        }

    }

}
