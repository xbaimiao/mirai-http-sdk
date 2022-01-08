package com.xbaimiao.mirai.packet.impl.websocket

import com.xbaimiao.mirai.config.SDKConfig
import com.xbaimiao.mirai.packet.Packet
import com.xbaimiao.mirai.packet.enums.HttpMethod
import java.net.URI
import java.net.http.HttpResponse
import java.net.http.WebSocket

/**
 * 绑定WebSocket 监听事件
 */
class BindWSPacket(config: SDKConfig) : Packet() {

    lateinit var webSocket: WebSocket
    lateinit var session: String
    var code = -999

    private val url = config.baseUrl.replace("http", "ws") + "all?verifyKey=${config.authKey}&qq=${config.qq}"
    override val httpMethod: HttpMethod = HttpMethod.GET
    override val targetedPath: String = "all"

    @Suppress("UNCHECKED_CAST")
    override fun <T : Packet> read(): T {
        while (code == -999) {
            Thread.sleep(100)
            return read()
        }
        return this as T
    }

    override fun sendAsync(func: HttpResponse<String>.() -> Unit) {
        webSocket = httpClient.newWebSocketBuilder().buildAsync(URI(url), WSListener(this)).join()
    }

    override fun send(func: HttpResponse<String>.() -> Unit) {
        webSocket = httpClient.newWebSocketBuilder().buildAsync(URI(url), WSListener(this)).join()
    }

    init {
        send()
    }

}