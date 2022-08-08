package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.MemberFriend

open class MemberLeaveEventQuit(
    val member: MemberFriend
) : SimpleEvent() {
}