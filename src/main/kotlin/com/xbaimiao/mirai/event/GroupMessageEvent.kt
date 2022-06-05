package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.message.MessageSource
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.message.serialize.MiraiSerializer

open class GroupMessageEvent(
    val group: Group,
    override val sender: MemberFriend,
    messageSource: MessageSource,
    component: BaseComponent
) : MessageEvent(sender, messageSource, component) {

    val plainText = MiraiSerializer.ComponentText.serialize(component)

    fun reply(baseComponent: BaseComponent) = group.quoteMessage(baseComponent)

    fun reply(string: String) = group.quoteMessage(Component.text(string))

}