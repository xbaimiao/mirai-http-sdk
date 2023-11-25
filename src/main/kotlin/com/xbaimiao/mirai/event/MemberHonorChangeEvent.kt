package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.MemberFriend

open class MemberHonorChangeEvent(
    val member: MemberFriend,
    val action: String,
    val honor: String
) : SimpleEvent()