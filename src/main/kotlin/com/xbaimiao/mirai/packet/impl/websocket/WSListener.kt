package com.xbaimiao.mirai.packet.impl.websocket

import com.google.gson.JsonParser
import com.xbaimiao.mirai.MiraiHttpSDK
import com.xbaimiao.mirai.packet.CommandPacket
import java.io.StringReader
import java.net.http.WebSocket
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

/**
 * 监听器
 */
class WSListener(private val bindWSPacket: BindWSPacket) : WebSocket.Listener {

    val putPackets = HashMap<Long, CommandPacket<*>>()

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
            if (!jsonObject.has("syncId")) {
                return CompletableFuture<Any>()
            }
            val syncId = jsonObject.get("syncId").asString
            if (syncId == "-1" || syncId == "") {
                //肯定是消息
                println(text)
                return CompletableFuture<Any>()
            }
            putPackets.keys.forEach {
                if (syncId.toLong() == it) {
                    putPackets[it]!!.put(jsonObject.toString())
                }
            }
            putPackets.remove(syncId.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return CompletableFuture<Any>()
    }

}