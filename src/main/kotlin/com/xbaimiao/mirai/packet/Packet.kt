package com.xbaimiao.mirai.packet

import com.xbaimiao.mirai.MiraiHttpSDK
import java.net.http.WebSocket
import java.util.concurrent.CompletableFuture

interface Packet<T> {

    val ws: WebSocket get() = MiraiHttpSDK.bot.webSocket
    val bindWSPacket get() = MiraiHttpSDK.bot

    fun send(): CompletableFuture<T>

}