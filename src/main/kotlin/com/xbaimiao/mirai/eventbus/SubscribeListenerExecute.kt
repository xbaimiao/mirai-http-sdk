package com.xbaimiao.mirai.eventbus

import com.xbaimiao.mirai.event.Event
import java.lang.reflect.Method

/**
 * 由 WCPE 在 2022/1/16 13:39 创建
 *
 * Created by WCPE on 2022/1/16 13:39
 *
 * GitHub  : https://github.com/wcpe
 * QQ      : 1837019522
 * @author : WCPE
 * @since  : v1.0.3-alpha-dev-1
 */
data class SubscribeListenerExecute(
    val listener: SubscribeListener,
    val method: Method,
    val subscribeHandler: SubscribeHandler
) {
    fun execute(event: Event) {
        method.invoke(listener, event)
    }
}