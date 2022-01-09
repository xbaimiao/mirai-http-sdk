package com.xbaimiao.mirai.entity

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.entity.MiraiMessageTransmittable.Factory.sendTo
import com.xbaimiao.mirai.entity.enums.Permission
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.packet.enums.MessageType
import java.util.concurrent.CompletableFuture

/**
 * 群成员
 */
class MemberFriend(
    id: Long,
    @SerializedName("memberName")
    override val nickName: String,
    @SerializedName("permission")
    val permission: Permission,
    /**
     * 群头衔
     */
    @SerializedName("specialTitle")
    val specialTitle: String,
    /**
     * 入群时间
     */
    @SerializedName("joinTimestamp")
    val joinTimestamp: Long,
    /**
     * 最后发言时间
     */
    @SerializedName("lastSpeakTimestamp")
    val lastSpeakTimestamp: Long,
    /**
     * 禁言时间
     */
    @SerializedName("muteTimeRemaining")
    val muteTimeRemaining: Long,
    @SerializedName("group")
    val group: Group
) : Friend(id, nickName, "") {

    override fun sendMessage(component: BaseComponent): CompletableFuture<Message> {
        return component.sendTo(this, MessageType.TEMP)
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

}