package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend

open class BotLeaveEventKick(
    val group: Group,
    val operator: MemberFriend
) : SimpleEvent()