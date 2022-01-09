package com.xbaimiao.mirai.message.impl

import com.xbaimiao.mirai.entity.MiraiMessageTransmittable
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent

class MessageImpl(
    override val target: MiraiMessageTransmittable,
    override val component: BaseComponent
) : Message {
    override var messageId = -9999
}
