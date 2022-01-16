package com.xbaimiao.mirai.eventbus

import com.xbaimiao.mirai.event.Cancellable
import com.xbaimiao.mirai.event.Event

object EventChancel {

    private val listenerMap = LinkedHashSet<SubscribeListenerExecute>()

    fun call(event: Event) {
        for (listenerExecute in listenerMap.filter { event::class.java == it.method.parameters[0].type }
            .sortedBy { it.subscribeHandler.priority }) {
            if (event is Cancellable && listenerExecute.subscribeHandler.ignoreCancelled && event.cancelled) {
                continue
            }
            listenerExecute.execute(event)
        }
    }

    fun registerSubscribeListener(listener: SubscribeListener) {
        val clazz = listener::class.java
        for (declaredMethod in clazz.declaredMethods) {
            listenerMap.add(
                SubscribeListenerExecute(
                    listener,
                    declaredMethod,
                    declaredMethod.getAnnotation(SubscribeHandler::class.java) ?: continue
                )
            )
        }
    }

    fun unregisterSubscribeListener(listener: SubscribeListener) {
        listenerMap.removeIf {
            it.listener.javaClass == listener.javaClass
        }
    }

}