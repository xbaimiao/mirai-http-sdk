package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend

open class GroupAllowMemberInviteEvent(
    val group: Group,
    val operator: MemberFriend,
    val origin: Boolean,
    val current: Boolean
) : SimpleEvent() {
}