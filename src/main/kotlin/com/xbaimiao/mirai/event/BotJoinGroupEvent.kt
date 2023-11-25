package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend

open class BotJoinGroupEvent(
    val group: Group,
    val invitor: MemberFriend?
) : SimpleEvent()