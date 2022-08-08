package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Group

open class BotLeaveEventActive(
    val group: Group
) : SimpleEvent() {
}