package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Friend
import com.xbaimiao.mirai.message.MessageSource
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.Component

class FriendMessageEvent(
    override val sender: Friend,
    messageSource: MessageSource,
    message: BaseComponent
) : MessageEvent(sender, messageSource, message) {

    fun reply(baseComponent: BaseComponent) = sender.sendMessage(baseComponent)

    fun reply(string: String) = sender.sendMessage(Component.text(string))

}