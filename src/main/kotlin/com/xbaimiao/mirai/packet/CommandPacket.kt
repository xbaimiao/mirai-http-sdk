package com.xbaimiao.mirai.packet

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.util.concurrent.CompletableFuture

abstract class CommandPacket<T>(
    @SerializedName("syncId")
    val syncId: Long = SyncIdPool.next(),
    @SerializedName("command")
    val command: String,
    @SerializedName("subCommand")
    val subCommand: String? = null,
    @SerializedName("content")
    val content: JsonObject = JsonObject()
) : Packet<T> {

    open fun serializerToJson(): String {
        return JsonObject().let {
            it.addProperty("syncId", syncId)
            it.addProperty("command", command)
            it.addProperty("subCommand", subCommand)
            it.add("content", content)
            it.toString()
        }
    }

    abstract val future: CompletableFuture<T>

    override fun send(): CompletableFuture<T> {
        println(this.serializerToJson())
        bindWSPacket.wsListener.putPackets[syncId] = this
        ws.sendText(this.serializerToJson(), true)
        return future
    }

    /**
     * 将从websocket收到的消息赋值进包内
     */
    abstract fun put(json: String): T

}