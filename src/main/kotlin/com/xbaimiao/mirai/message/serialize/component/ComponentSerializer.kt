package com.xbaimiao.mirai.message.serialize.component

import com.xbaimiao.mirai.message.serialize.component.impl.ComponentJsonSerializerImpl
import com.xbaimiao.mirai.message.serialize.component.impl.ComponentPlainTextSerializerImpl

interface ComponentSerializer {

    companion object {
        @JvmStatic
        @get:JvmName("json")
        val json: ComponentJsonSerializer = ComponentJsonSerializerImpl

        @JvmStatic
        @get:JvmName("plainText")
        val plainText: ComponentPlainTextSerializer = ComponentPlainTextSerializerImpl
    }
}
