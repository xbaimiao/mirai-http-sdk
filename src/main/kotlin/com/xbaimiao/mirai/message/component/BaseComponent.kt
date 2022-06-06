package com.xbaimiao.mirai.message.component

import com.google.gson.JsonObject

interface BaseComponent {
    val children: List<BaseComponent>
    fun append(other: BaseComponent): BaseComponent
    fun toList(): List<BaseComponent>

    /**
     * 转换为 JSON 对象
     */
    fun serializeToJson(): JsonObject

    /**
     * 转为文本
     */
    fun serializeToPlainText(): String
    operator fun plus(other: BaseComponent): BaseComponent

    /**
     * 将消息内容转为文本
     */
    fun contentToString(): String

}
