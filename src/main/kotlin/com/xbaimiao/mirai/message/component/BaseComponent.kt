package com.xbaimiao.mirai.message.component

import com.google.gson.JsonArray

interface BaseComponent {
    val children: List<BaseComponent>

    fun append(other: BaseComponent): BaseComponent
    fun toList(): List<BaseComponent>
    fun flattenToList(): List<BaseComponent>
    fun serializeToJson(): JsonArray
    fun serializeToPlainText(): String
    operator fun plus(other: BaseComponent): BaseComponent
}
