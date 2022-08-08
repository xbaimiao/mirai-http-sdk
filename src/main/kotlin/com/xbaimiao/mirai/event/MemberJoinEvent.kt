package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend

open class MemberJoinEvent(
    val group: Group,
    val member: MemberFriend,
    val invitor: MemberFriend?
) : SimpleEvent() {
}