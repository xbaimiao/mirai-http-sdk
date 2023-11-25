package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.MemberFriend

open class MemberPermissionChangeEvent(
    val member: MemberFriend,
    val origin: String,
    val current: String
) : SimpleEvent()