package com.xbaimiao.mirai.event

open class MemberJoinRequestEvent(
    val eventId: Long,
    val fromId: Long,
    val groupId: Long,
    val groupName: String,
    val nick: String,
    val message: String
) : SimpleEvent() {
}