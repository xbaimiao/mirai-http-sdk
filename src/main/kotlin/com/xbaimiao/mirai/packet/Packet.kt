package com.xbaimiao.mirai.packet

import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot
import org.java_websocket.client.WebSocketClient
import java.util.concurrent.CompletableFuture

interface Packet<T> {

    val ws: WebSocketClient get() = WebSocketBot.bot.webSocket
    val bindWSPacket get() = WebSocketBot.bot

    fun send(): CompletableFuture<T>

}