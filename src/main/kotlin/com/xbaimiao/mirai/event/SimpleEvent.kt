package com.xbaimiao.mirai.event

abstract class SimpleEvent: Event(), Cancellable {
    override var cancelled: Boolean = false
}