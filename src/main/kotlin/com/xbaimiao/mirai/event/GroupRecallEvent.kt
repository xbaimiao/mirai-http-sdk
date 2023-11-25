package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend

open class GroupRecallEvent(
    val group: Group,
    val authorId: Long,
    val messageId: Int,
    val time: Int,
    val operator: MemberFriend
) : SimpleEvent()