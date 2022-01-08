package com.xbaimiao.mirai.entity

import com.xbaimiao.mirai.message.Message

interface MiraiMessageTransmittable {

    /**
     * 发送文本消息
     */
    fun reply(message: String)

    /**
     * 发送消息
     */
    fun reply(message: Message)

}