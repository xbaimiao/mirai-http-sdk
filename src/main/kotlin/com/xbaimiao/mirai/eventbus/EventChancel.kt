package com.xbaimiao.mirai.eventbus

import com.xbaimiao.mirai.event.Cancellable
import com.xbaimiao.mirai.event.Event
import java.util.LinkedList

object EventChancel : SubscribeListener {

    private val listenerMap = LinkedHashSet<SubscribeListenerExecute>()

    val funcListenerList = LinkedList<FuncListener>()

    fun call(event: Event) {
        funcListenerList.asSequence().filter { it.clazz == event.javaClass }.forEach {
            it.func.invoke(event)
        }
        for (listenerExecute in listenerMap.filter { event::class.java == it.method.parameters[0].type }
            .sortedBy { it.subscribeHandler.priority }) {
            if (event is Cancellable && listenerExecute.subscribeHandler.ignoreCancelled && event.cancelled) {
                continue
            }
            listenerExecute.execute(event)
        }
    }

    fun subscribe(listener: SubscribeListener) {
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

    @Suppress("Unchecked_cast")
    inline fun <reified T : Event> subscribe(noinline func: T.() -> Unit) {
        funcListenerList.add(FuncListener(T::class.java, func as Event.() -> Unit))
    }

    fun unsubscribe(listener: SubscribeListener) {
        listenerMap.removeIf {
            it.listener.javaClass == listener.javaClass
        }
    }

}