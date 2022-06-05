package com.xbaimiao.mirai.entity

import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.impl.MessageImpl
import com.xbaimiao.mirai.message.serialize.MiraiSerializer
import com.xbaimiao.mirai.packet.enums.MessageType
import com.xbaimiao.mirai.packet.impl.group.MessagePacket
import java.util.concurrent.CompletableFuture

interface MiraiMessageTransmittable {

    val id: Long

    /**
     * 发送消息
     */
    fun quoteMessage(component: BaseComponent): CompletableFuture<Message>

    /**
     * 回复消息
     */
    fun quoteMessage(component: BaseComponent, quote: String): CompletableFuture<Message>

    companion object Factory {
        internal fun BaseComponent.sendTo(
            miraiMessageTransmittable: MiraiMessageTransmittable,
            type: MessageType,
            quote: String? = null
        ): CompletableFuture<Message> {
            val message = MessageImpl(miraiMessageTransmittable, this)

            val jsonObject = MiraiSerializer.MessageSerialize.serialize(message)
            if (quote != null) {
                jsonObject.addProperty("quote", quote)
            }
            val packet = MessagePacket(jsonObject, type)
            val future = CompletableFuture<Message>()
            packet.send().thenAcceptAsync {
                message.messageId = it.messageId
                future.complete(message)
            }
            return future
        }
    }
}
