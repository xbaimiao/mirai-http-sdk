package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group

open class GroupPermissionChangeEvent(
    val group: Group,
    val origin: String,
    val current: String
) : SimpleEvent() {
}