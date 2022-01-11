package com.xbaimiao.mirai.eventbus

import com.xbaimiao.mirai.event.Event
import org.greenrobot.eventbus.EventBus

object EventChancel {

    private val eventbus = EventBus.builder().sendNoSubscriberEvent(false).logNoSubscriberMessages(false).build()

    fun call(event: Event) {
        eventbus.post(event)
    }

    fun registerListener(any: Any) {
        eventbus.register(any)
    }

    fun unregisterListener(any: Any) {
        eventbus.unregister(any)
    }

}