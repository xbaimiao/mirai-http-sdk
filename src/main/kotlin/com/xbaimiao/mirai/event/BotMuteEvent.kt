package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend

open class BotMuteEvent(
    val group: Group,
    val durationSeconds: Int,
    val operator: MemberFriend
) : SimpleEvent()