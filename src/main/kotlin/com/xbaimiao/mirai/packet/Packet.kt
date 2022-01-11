package com.xbaimiao.mirai.packet

import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot
import java.net.http.WebSocket
import java.util.concurrent.CompletableFuture

interface Packet<T> {

    val ws: WebSocket get() = WebSocketBot.bot.webSocket
    val bindWSPacket get() = WebSocketBot.bot

    fun send(): CompletableFuture<T>

}