package com.xbaimiao.mirai.event

import com.xbaimiao.mirai.entity.Friend
import com.xbaimiao.mirai.message.component.BaseComponent

class FriendMessageEvent(
    friend: Friend,
    component: BaseComponent
) : MessageEvent(friend, component) {
}