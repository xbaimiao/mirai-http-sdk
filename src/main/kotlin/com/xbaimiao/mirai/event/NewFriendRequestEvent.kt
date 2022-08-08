package com.xbaimiao.mirai.event

open class NewFriendRequestEvent(
    val eventId: Long,
    val fromId: Long,
    val groupId: Long,
    val nick: String,
    val message: String
) : SimpleEvent() {
}