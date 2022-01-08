package com.xbaimiao.mirai.packet

import com.google.gson.Gson
import com.xbaimiao.mirai.MiraiHttpSDK
import com.xbaimiao.mirai.packet.enums.HttpMethod
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.time.temporal.ChronoUnit

abstract class Packet {

    companion object {
        val gson = Gson()
        val httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(6, ChronoUnit.SECONDS)).build()
    }

    abstract val httpMethod: HttpMethod
    abstract val targetedPath: String
    abstract fun <T : Packet> read(): T

    open fun toJson(): String {
        return gson.toJson(this)
    }

    open fun send(func: HttpResponse<String>.() -> Unit = {}) {
        func.invoke(httpClient.send(buildRequest(), HttpResponse.BodyHandlers.ofString()))
    }

    open fun sendAsync(func: HttpResponse<String>.() -> Unit = {}) {
        httpClient.sendAsync(buildRequest(), HttpResponse.BodyHandlers.ofString()).thenAcceptAsync {
            func.invoke(it)
        }
    }

    fun buildRequest(): HttpRequest {
        val packet: String = toJson()
        var builder = HttpRequest.newBuilder(URI("${MiraiHttpSDK.sdkConfig.baseUrl}$targetedPath"))
        builder = if (httpMethod == HttpMethod.GET) {
            builder.GET()
        } else {
            builder.POST(HttpRequest.BodyPublishers.ofString(packet))
        }
        builder.version(HttpClient.Version.HTTP_1_1)
        return builder.build()
    }

}