package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.MemberFriend

open class MemberLeaveEventKick(
    val operator: MemberFriend,
    val member: MemberFriend
) : SimpleEvent()