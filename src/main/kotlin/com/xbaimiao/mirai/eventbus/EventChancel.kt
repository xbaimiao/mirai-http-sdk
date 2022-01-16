package com.xbaimiao.mirai.eventbus

import com.xbaimiao.mirai.event.Cancellable
import com.xbaimiao.mirai.event.Event
import kotlin.collections.LinkedHashMap

object EventChancel {

    private val listenerMap = LinkedHashSet<SubscribeListenerExecute>()

    fun call(event: Event) {
        for (listenerExecute in listenerMap.filter { event::class.java == it.method.parameters[0].type }
            .sortedBy { it.subscribeHandler.priority }) {
            if (event is Cancellable) {
                if (listenerExecute.subscribeHandler.ignoreCancelled && event.cancelled) {
                    continue
                }
            }
            listenerExecute.execute(event)
        }
    }

    fun registerSubscribeListener(listener: SubscribeListener) {
        val clazz = listener::class.java
        for (declaredMethod in clazz.declaredMethods) {
            val subscribeHandler = declaredMethod.getAnnotation(SubscribeHandler::class.java) ?: continue
            listenerMap.add(SubscribeListenerExecute(listener, declaredMethod, subscribeHandler))
        }
    }

    fun unregisterSubscribeListener(listener: SubscribeListener) {
        listenerMap.removeIf {
            it.listener.javaClass == listener.javaClass
        }
    }

}