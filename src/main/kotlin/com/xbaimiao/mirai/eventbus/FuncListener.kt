package com.xbaimiao.mirai.eventbus

import com.xbaimiao.mirai.event.Event

class FuncListener(
    val clazz: Class<out Event>,
    val func: Event.() -> Unit
)