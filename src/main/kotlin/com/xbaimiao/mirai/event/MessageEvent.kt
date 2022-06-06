package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.MiraiMessageTransmittable
import com.xbaimiao.mirai.message.MessageSource
import com.xbaimiao.mirai.message.component.BaseComponent

abstract class MessageEvent(
    open val sender: MiraiMessageTransmittable,
    open val messageSource: MessageSource,
    val message: BaseComponent
) : Event(), Cancellable {
    override var cancelled: Boolean = false
}