package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group

open class GroupAllowConfessTalkEvent(
    val group: Group,
    val origin: Boolean,
    val current: Boolean,
    val ByBot: Boolean
) : SimpleEvent() {
}