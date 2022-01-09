package com.xbaimiao.mirai.entity

import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.impl.MessageImpl
import com.xbaimiao.mirai.message.serialize.MessageSerializer
import com.xbaimiao.mirai.packet.enums.MessageType
import com.xbaimiao.mirai.packet.impl.group.MessagePacket
import java.util.concurrent.CompletableFuture

interface MiraiMessageTransmittable {

    companion object Factory {

        fun BaseComponent.sendTo(
            miraiMessageTransmittable: MiraiMessageTransmittable,
            type: MessageType
        ): CompletableFuture<Message> {
            val message = MessageImpl(miraiMessageTransmittable, this)
            val jsonObject = MessageSerializer.Json.serialize(message)
            val packet = MessagePacket(jsonObject, type)
            val future = CompletableFuture<Message>()
            packet.send().thenAcceptAsync {
                message.messageId = it.messageId
                future.complete(message)
            }
            return future
        }

    }

    val id: Long

    /**
     * 发送消息
     */
    fun sendMessage(component: BaseComponent): CompletableFuture<Message>

}
