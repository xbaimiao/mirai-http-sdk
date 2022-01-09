package com.xbaimiao.mirai.event.group

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.event.Event
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.serialize.impl.TextSerializer

class GroupMessageEvent(
    val group: Group,
    val sender: MemberFriend,
    val component: BaseComponent
) : Event() {

    val plainText = TextSerializer.serialize(component)

    fun reply(baseComponent: BaseComponent) = group.sendMessage(baseComponent)

}