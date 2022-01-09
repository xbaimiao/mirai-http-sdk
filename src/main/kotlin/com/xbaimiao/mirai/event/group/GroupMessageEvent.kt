package com.xbaimiao.mirai.event.group

import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.event.Event
import com.xbaimiao.mirai.message.component.Component

class GroupMessageEvent(
    val group: Group,
    val message: Component
) : Event() {

}