package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend

open class BotUnMuteEvent(
    val group: Group,
    val operator: MemberFriend
) : SimpleEvent()