package com.xbaimiao.mirai.eventbus

import com.xbaimiao.mirai.event.Cancellable
import com.xbaimiao.mirai.event.Event

object EventChancel : SubscribeListener {

    private val listenerMap = LinkedHashSet<SubscribeListenerExecute>()

    val funcListenerMap = LinkedHashMap<Class<out Event>, Event.() -> Unit>()

    fun call(event: Event) {
        funcListenerMap.asSequence().filter { it.key == event.javaClass }.forEach {
            it.value.invoke(event)
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
        funcListenerMap[T::class.java] = func as Event.() -> Unit
    }

    fun unsubscribe(listener: SubscribeListener) {
        listenerMap.removeIf {
            it.listener.javaClass == listener.javaClass
        }
    }

}