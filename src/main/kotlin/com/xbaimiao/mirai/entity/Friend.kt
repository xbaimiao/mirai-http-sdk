package com.xbaimiao.mirai.entity

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.entity.MiraiMessageTransmittable.Factory.sendTo
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.packet.enums.MessageType
import java.util.concurrent.CompletableFuture

open class Friend(
    /**
     * qq号
     */
    @SerializedName("id")
    override val id: Long,
    /**
     * qq名
     */
    @SerializedName("nickname")
    open val nickName: String,
    /**
     * 备注 当为群成员时,此项为 ""
     */
    @SerializedName("remark")
    val remark: String
) : MiraiMessageTransmittable {

    override fun sendMessage(component: BaseComponent): CompletableFuture<Message> {
        return component.sendTo(this,MessageType.FRIEND)
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

}