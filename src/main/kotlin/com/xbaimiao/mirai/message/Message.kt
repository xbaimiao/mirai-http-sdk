package com.xbaimiao.mirai.message

import com.xbaimiao.mirai.entity.MiraiMessageTransmittable
import com.xbaimiao.mirai.message.component.BaseComponent

interface Message {
    val target: MiraiMessageTransmittable
    val component: BaseComponent

    /**
     * 消息id 由于是http 需要消息成功发送后才能获取
     */
    val messageId: Int
}
