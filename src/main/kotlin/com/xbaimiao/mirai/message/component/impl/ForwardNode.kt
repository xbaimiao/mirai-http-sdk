package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.serialize.MiraiSerializer

class ForwardNode(
    @SerializedName("senderId")
    val senderId: Long,

    @SerializedName("time")
    val time: Long,

    @SerializedName("senderName")
    val senderName: String,

    @SerializedName("messageChain")
    val messageChainOrigin: List<JsonObject>,

    @SerializedName("messageId")
    val messageId: Long?
) {
    private var messages: List<BaseComponent>? = null

    constructor(
        senderId: Long,
        time: Long,
        senderName: String,
        messages: List<BaseComponent>
    ) : this(senderId, time, senderName, messageToOrigin(messages), null)

    fun getMessages(): List<BaseComponent> {
        if (messages == null) {
            messages = originToMessage(messageChainOrigin)
        }
        return messages!!
    }

    companion object {
        fun messageToOrigin(messageChain: List<BaseComponent>): List<JsonObject> {
            val list: MutableList<JsonObject> = ArrayList()
            messageChain.forEach {
                list.add(it.serializeToJson())
            }
            return list
        }

        fun originToMessage(messageChainOrigin: List<JsonObject>): List<BaseComponent> {
            val list: MutableList<BaseComponent> = ArrayList()
            list.addAll(MiraiSerializer.ComponentSerializer.deserialize(JsonArray().apply {
                messageChainOrigin.forEach { jsonObject -> add(jsonObject) }
            }).children)
            return list
        }
    }
}