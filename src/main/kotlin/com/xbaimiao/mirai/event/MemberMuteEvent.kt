package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.MemberFriend

open class MemberMuteEvent(
    val durationSeconds: Int,
    val operator: MemberFriend,
    val member: MemberFriend
) : SimpleEvent() {
}