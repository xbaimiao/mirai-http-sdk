package com.xbaimiao.mirai.entity

import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.packet.enums.MessageType

interface MiraiMessageTransmittable {

    val id: Long

    val type: MessageType

    /**
     * 发送消息
     */
    fun send(message: BaseComponent)
}
