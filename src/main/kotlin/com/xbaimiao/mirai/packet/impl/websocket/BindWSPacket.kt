package com.xbaimiao.mirai.packet.impl.websocket

import com.xbaimiao.mirai.config.SDKConfig
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.time.Duration
import java.time.temporal.ChronoUnit

/**
 * 绑定WebSocket 监听事件
 */
class BindWSPacket(config: SDKConfig) {

    var httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(6, ChronoUnit.SECONDS)).build()
    lateinit var webSocket: WebSocket
    lateinit var wsListener: WSListener

    private val url = config.baseUrl.replace("http", "ws") + "all?verifyKey=${config.authKey}&qq=${config.qq}"

    fun connect(): BindWSPacket {
        webSocket =
            httpClient.newWebSocketBuilder().buildAsync(URI(url), WSListener(this).also { wsListener = it }).join()
        return this
    }

}