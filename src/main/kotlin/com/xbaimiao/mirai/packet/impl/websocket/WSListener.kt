package com.xbaimiao.mirai.packet.impl.websocket

import com.google.gson.JsonParser
import com.xbaimiao.mirai.MiraiHttpSDK
import java.io.StringReader
import java.net.http.WebSocket
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

/**
 * 监听器
 */
class WSListener(val bindWSPacket: BindWSPacket) : WebSocket.Listener {

    override fun onOpen(webSocket: WebSocket) {
        webSocket.request(1)
    }

    override fun onClose(webSocket: WebSocket?, statusCode: Int, reason: String?): CompletionStage<*>? {
        return null
    }

    override fun onError(webSocket: WebSocket, error: Throwable) {
        error.printStackTrace()
        MiraiHttpSDK.init()
    }

    override fun onText(webSocket: WebSocket, charSequence: CharSequence, last: Boolean): CompletionStage<*> {
        webSocket.request(1)
        val text = charSequence.toString()
        try {
            val jsonObject = JsonParser.parseReader(StringReader(text)).asJsonObject
            when (jsonObject.get("syncId").asString) {
                "" -> {
                    jsonObject.get("data").asJsonObject.let { data ->
                        bindWSPacket.session = data.get("session").asString
                        bindWSPacket.code = data.get("code").asInt
                    }
                }
                "-1" -> {
                    println(jsonObject.toString())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return CompletableFuture<Any>()
    }

}