package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.MiraiMessageTransmittable
import com.xbaimiao.mirai.message.component.BaseComponent

abstract class MessageEvent(
    val sender: MiraiMessageTransmittable,
    val component: BaseComponent
) : Event() {
}