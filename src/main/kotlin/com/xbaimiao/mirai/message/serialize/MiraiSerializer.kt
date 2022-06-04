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
            value.component.toList().map{
                it.serializeToJson()
            }.forEach(jsonArray::add)
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
                    ComponentType.PLAIN -> component += PlainText(it.get("text").asString)
                    ComponentType.IMAGE -> component += Image(it)
                    ComponentType.AT -> component += At(it.get("target").asLong, it.get("display").asString)
                    ComponentType.FACE -> component += Emoji(it.get("faceId").asInt, it.get("name").asString)
                    ComponentType.AT_ALL -> component += AtAll()
                    ComponentType.MUSIC -> component += Music(MusicType.StringToMusicType(it.get("kind").asString),it.get("title").asString,it.get("summary").asString,it.get("jumpUrl").asString,it.get("pictureUrl").asString,it.get("musicUrl").asString,it.get("brief").asString)
                    ComponentType.QUOTE -> component += Quote(it.get("id").asInt,it.get("groupId").asLong,it.get("senderId").asLong,it.get("targetId").asLong,it.get("origin").asJsonArray)
                    ComponentType.FLASH_IMAGE -> {
                        var imageId = ""
                        if (!it.get("imageId").isJsonNull){
                            imageId = it.get("imageId").asString
                        }
                        var url = ""
                        if (!it.get("url").isJsonNull){
                            url = it.get("url").asString;
                        }
                        var base64 = ""
                        if (!it.get("base64").isJsonNull){
                            base64 = it.get("base64").asString
                        }
                        component += FlashImage(imageId,url, base64)
                    }
                    ComponentType.VOICE -> {
                        var base64 = ""
                        var voiceId = ""
                        if (!it.get("base64").isJsonNull){
                            base64 = it.get("base64").asString;
                        }
                        if (!it.get("voiceId").isJsonNull){
                            voiceId = it.get("voiceId").asString
                        }
                        component += Voice(voiceId,it.get("url").asString,base64,it.get("length").asLong)
                    }
                    ComponentType.POKE -> component += Poke(PokeType.StringToType(it.get("name").asString))
                    ComponentType.MIRAI -> component += MiraiCode(it.get("code").asString)
                    ComponentType.DICE -> component += Dice(it.get("value").asInt)
                    ComponentType.JSON -> component += Json(it.get("json").asString)
                    ComponentType.SOURCE -> {}
                    ComponentType.NULL -> {}
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
