package com.xbaimiao.mirai.packet.enums

import com.xbaimiao.mirai.entity.Friend
import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MiraiMessageTransmittable

enum class EntityType(
    val command: String,
    val clazz: Class<out MiraiMessageTransmittable>
) {
    GROUP("groupList", Group::class.java),
    FRIEND("friendList", Friend::class.java),
    GROUP_FRIEND_LIST("memberList", Friend::class.java)
}