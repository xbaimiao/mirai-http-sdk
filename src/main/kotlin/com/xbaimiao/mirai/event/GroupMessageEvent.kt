package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.message.MessageSource
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.Component

open class GroupMessageEvent(
    val group: Group,
    override val sender: MemberFriend,
    messageSource: MessageSource,
    message: BaseComponent
) : MessageEvent(sender, messageSource, message) {

    fun reply(baseComponent: BaseComponent) = group.sendMessage(baseComponent)

    fun reply(string: String) = group.sendMessage(Component.text(string))

}