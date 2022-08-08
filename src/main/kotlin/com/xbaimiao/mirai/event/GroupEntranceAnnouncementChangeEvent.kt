package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend

open class GroupEntranceAnnouncementChangeEvent(
    val group: Group,
    val operator: MemberFriend,
    val origin: String,
    val current: String
) : SimpleEvent() {
}