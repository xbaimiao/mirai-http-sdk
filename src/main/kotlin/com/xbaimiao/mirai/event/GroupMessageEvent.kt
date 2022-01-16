package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.impl.PlainText
import com.xbaimiao.mirai.message.serialize.MiraiSerializer

open class GroupMessageEvent(
    val group: Group,
    override val sender: MemberFriend,
    component: BaseComponent
) : MessageEvent(sender, component) {

    val plainText = MiraiSerializer.ComponentText.serialize(component)

    fun reply(baseComponent: BaseComponent) = group.sendMessage(baseComponent)

    fun reply(string: String) = group.sendMessage(PlainText(string))

}