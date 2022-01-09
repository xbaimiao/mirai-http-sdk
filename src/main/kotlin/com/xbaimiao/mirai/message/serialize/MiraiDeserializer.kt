package com.xbaimiao.mirai.message.serialize

interface MiraiDeserializer<I, O> {
    fun deserialize(value: I): O
}