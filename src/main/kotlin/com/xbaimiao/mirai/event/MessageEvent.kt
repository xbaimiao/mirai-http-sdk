package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.MiraiMessageTransmittable
import com.xbaimiao.mirai.eventbus.EventChancel
import com.xbaimiao.mirai.message.component.BaseComponent

abstract class MessageEvent(
    open val sender: MiraiMessageTransmittable,
    val component: BaseComponent
) : Event(), Cancellable {
    override var cancelled: Boolean = false
}