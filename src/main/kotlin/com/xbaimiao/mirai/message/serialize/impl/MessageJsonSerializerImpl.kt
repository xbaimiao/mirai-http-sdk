package com.xbaimiao.mirai.message.serialize.impl

import com.google.gson.JsonObject
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.serialize.ComponentSerializer
import com.xbaimiao.mirai.message.serialize.MessageJsonSerializer

object MessageJsonSerializerImpl : MessageJsonSerializer {

    override fun serialize(value: Message): JsonObject {
        val jsonObject = JsonObject()
        if (value.target is MemberFriend) {
            jsonObject.addProperty("qq", value.target.id)
            jsonObject.addProperty("group", (value.target as MemberFriend).group.id)
        } else {
            jsonObject.addProperty("target", value.target.id)
        }
        jsonObject.add("messageChain", ComponentSerializer.json.serialize(value.component))
        return jsonObject
    }

    override fun deserialize(value: JsonObject): Message {
        TODO()
    }
}
