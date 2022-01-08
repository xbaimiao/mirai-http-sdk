package com.xbaimiao.mirai.message.component

interface BaseComponent {
    val children: List<BaseComponent>

    fun append(other: BaseComponent): BaseComponent
    operator fun plus(other: BaseComponent): BaseComponent
}
