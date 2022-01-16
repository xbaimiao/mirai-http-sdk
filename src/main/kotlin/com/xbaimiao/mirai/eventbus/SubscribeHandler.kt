package com.xbaimiao.mirai.eventbus

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * 由 WCPE 在 2022/1/16 13:25 创建
 *
 * Created by WCPE on 2022/1/16 13:25
 *
 * GitHub  : https://github.com/wcpe
 * QQ      : 1837019522
 * @author : WCPE
 * @since  : v1.0.3-alpha-dev-1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
annotation class SubscribeHandler(
    /**
     * SubscribePriority.LOWEST
     * SubscribePriority.LOW
     * SubscribePriority.NORMAL
     * SubscribePriority.HIGH
     * SubscribePriority.HIGHEST
     * SubscribePriority.MONITOR
     * @return 这个处理器的优先级
     * @see com.xbaimiao.mrai.eventbus.SubscribeHandler
     */
    val priority: SubscribePriority = SubscribePriority.NORMAL,
    /**
     * 定义这个处理器是否忽略被取消的事件
     * 如果为`true`而且事件发生，这个处理器不会被调用，反之相反
     *
     * @return 这个处理器是否忽略被取消的事件
     * @see com.xbaimiao.mrai.eventbus.SubscribeHandler
     */
    val ignoreCancelled: Boolean = false
)