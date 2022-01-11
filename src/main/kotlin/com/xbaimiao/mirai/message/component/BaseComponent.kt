package com.xbaimiao.mirai.message.component

import com.google.gson.JsonObject

interface BaseComponent {
    val children: List<BaseComponent>

    fun append(other: BaseComponent): BaseComponent
    fun toList(): List<BaseComponent>
    fun serializeToJson(): JsonObject
    fun serializeToPlainText(): String
    operator fun plus(other: BaseComponent): BaseComponent

}
