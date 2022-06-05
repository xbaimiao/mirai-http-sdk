package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Friend
import com.xbaimiao.mirai.message.MessageSource
import com.xbaimiao.mirai.message.component.BaseComponent

class FriendMessageEvent(
    override val sender: Friend,
    messageSource: MessageSource,
    component: BaseComponent
) : MessageEvent(sender, messageSource, component) {
}