package com.xbaimiao.mirai.message

import com.xbaimiao.mirai.entity.MiraiMessageTransmittable
import com.xbaimiao.mirai.message.component.BaseComponent

interface Message {
    val target: MiraiMessageTransmittable
    val component: BaseComponent
}
