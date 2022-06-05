package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.message.MessageSource
import com.xbaimiao.mirai.message.component.BaseComponent

class GroupTempMessageEvent(
    group: Group,
    override val sender: MemberFriend,
    messageSource: MessageSource,
    component: BaseComponent
) : GroupMessageEvent(
    group, sender, messageSource, component
) {
}