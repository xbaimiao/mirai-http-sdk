package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.MemberFriend

open class FriendRecallEvent(
    val authorId: Long,
    val messageId: Int,
    val time: Int,
    val operator: MemberFriend
) : SimpleEvent()