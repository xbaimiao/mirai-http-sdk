@file:Suppress("unused")

package com.xbaimiao.mirai.message.serialize

import com.xbaimiao.mirai.message.serialize.impl.MessageJsonSerializerImpl

interface MessageSerializer {

    companion object {
        @JvmStatic
        @get:JvmName("json")
        val json = MessageJsonSerializerImpl

        @JvmStatic
        @get:JvmName("plainText")
        val plainText = MessageJsonSerializerImpl
    }
}
