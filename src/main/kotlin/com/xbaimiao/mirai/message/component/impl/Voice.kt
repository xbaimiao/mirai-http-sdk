package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList

class Voice(
    val voiceId: String,
    val url: String,
    val base64: String,
    val length: Long,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    constructor(voiceId: String, url: String, base64: String, length: Long) : this(
        voiceId,
        url,
        base64,
        length,
        ComponentList()
    )

    override fun fromChildren(children: List<BaseComponent>) = Voice(voiceId, url, base64, length, children)

    override fun serializeToJson(): JsonObject {
        return JsonObject().apply {
            addProperty("type", "Voice")
            //当VoiceId为JsonNull时将支持URL
            //VoiceId优先级高于URL
            if (voiceId == "") {
                add("voiceId", JsonNull.INSTANCE)
            } else {
                addProperty("voiceId", voiceId)
            }
            if (url == "") {
                add("url", JsonNull.INSTANCE)
            } else {
                addProperty("url", url)
            }
            add("path", JsonNull.INSTANCE)
            if (base64 == "") {
                add("base64", JsonNull.INSTANCE)
            } else {
                addProperty("base64", base64)
            }
            addProperty("length", length)
        }
    }

    override fun serializeToPlainText(): String {
        return "[语音] 时长: $length";
    }

}
