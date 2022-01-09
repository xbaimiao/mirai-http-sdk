package com.xbaimiao.mirai.message.serialize

interface MiraiSerializer<I, O> {
    fun serialize(value: I): O
}