package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList

class Music(
    val type: MusicType,
    val title: String,
    val summary: String,
    val jumpUrl: String,
    val pictureUrl: String,
    val musicUrl: String,
    val brief: String,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    constructor(
        type: MusicType,
        title: String,
        summary: String,
        jumpUrl: String,
        pictureUrl: String,
        musicUrl: String,
        brief: String
    ) : this(type, title, summary, jumpUrl, pictureUrl, musicUrl, brief, ComponentList())

    override fun fromChildren(children: List<BaseComponent>) =
        Music(type, title, summary, jumpUrl, pictureUrl, musicUrl, brief, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", "MusicShare")
            addProperty("kind", MusicType.MusicTypeConverter(type))
            addProperty("title", title)
            addProperty("summary", summary)
            addProperty("jumpUrl", jumpUrl)
            addProperty("pictureUrl", pictureUrl)
            addProperty("musicUrl", musicUrl)
            addProperty("brief", brief)
        }
    }

    override fun serializeToPlainText(): String {
        return brief
    }

}
