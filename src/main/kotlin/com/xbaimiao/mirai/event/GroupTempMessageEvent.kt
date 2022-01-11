package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.message.component.BaseComponent

class GroupTempMessageEvent(
    group: Group,
    sender: MemberFriend,
    component: BaseComponent
) : GroupMessageEvent(
    group, sender, component
) {
}