package com.xbaimiao.mirai.entity

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.entity.MiraiMessageTransmittable.Factory.sendTo
import com.xbaimiao.mirai.entity.enums.Permission
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.packet.enums.MessageType
import com.xbaimiao.mirai.packet.impl.group.MuteMemberFriendPacket
import com.xbaimiao.mirai.packet.impl.group.UnMuteMemberFriendPacket
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

    /**
     *  禁言
     */
    fun mute(time: Int): CompletableFuture<MuteMemberFriendPacket.Result> {
        val future = CompletableFuture<MuteMemberFriendPacket.Result>()
        MuteMemberFriendPacket(id, group.id, time).send().thenApplyAsync {
            future.complete(it.result)
        }
        return future
    }

    /**
     * 解除禁言
     */
    fun unmute(): CompletableFuture<UnMuteMemberFriendPacket.Result> {
        val future = CompletableFuture<UnMuteMemberFriendPacket.Result>()
        UnMuteMemberFriendPacket(id, group.id).send().thenApplyAsync {
            future.complete(it.result)
        }
        return future
    }

    override fun quoteMessage(component: BaseComponent, quote: String): CompletableFuture<Message> {
        return component.sendTo(this, MessageType.TEMP, quote)
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

}