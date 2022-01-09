package com.xbaimiao.mirai.entity

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.packet.enums.MessageType

class Friend(
    /**
     * qq号
     */
    @SerializedName("id")
    override val id: Long,
    /**
     * qq名
     */
    @SerializedName("nickname")
    val nickName: String,
    /**
     * 备注
     */
    @SerializedName("remark")
    val remark: String
) : MiraiMessageTransmittable {
    override val type: MessageType = MessageType.FRIEND

    override fun sendMessage(message: BaseComponent) {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

}