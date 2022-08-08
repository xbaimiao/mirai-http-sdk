package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.MessageSubject

open class NudgeEvent(
    val fromId: Long,
    val subject: MessageSubject,
    val action: String,
    val suffix: String,
    val target: Long
) : SimpleEvent() {
}