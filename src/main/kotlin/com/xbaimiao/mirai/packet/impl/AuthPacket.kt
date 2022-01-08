package com.xbaimiao.mirai.packet.impl

import com.google.gson.JsonParser
import com.xbaimiao.mirai.MiraiHttpSDK
import com.xbaimiao.mirai.packet.enums.HttpMethod
import com.xbaimiao.mirai.packet.Packet
import java.io.StringReader

class AuthPacket(
    val verifyKey: String = MiraiHttpSDK.sdkConfig.authKey
) : Packet() {

    /**
     * 认证状态码
     */
    var code: Int = -999

    /**
     * 认证返回的 session
     */
    lateinit var session: String

    override val httpMethod = HttpMethod.POST
    override val targetedPath = "verify"

    override fun toString(): String {
        return gson.toJson(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Packet> read(): T {
        this.send {
            JsonParser.parseReader(StringReader(this.body())).asJsonObject.apply {
                code = this.get("code").asInt
                session = this.get("session").asString
            }
        }
        return this as T
    }

}