package com.xbaimiao.mirai.eventbus

/**
 * 由 WCPE 在 2022/1/16 13:26 创建
 *
 * Created by WCPE on 2022/1/16 13:26
 *
 * GitHub  : https://github.com/wcpe
 * QQ      : 1837019522
 * @author : WCPE
 * @since  : v1.0.3-alpha-dev-1
 */
enum class SubscribePriority(val priority: Int) {
    LOWEST(1),
    LOW(2),
    NORMAL(3),
    HIGH(4),
    HIGHEST(5),
    MONITOR(6)
}