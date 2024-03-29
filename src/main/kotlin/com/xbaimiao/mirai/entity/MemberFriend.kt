package com.xbaimiao.mirai.entity

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.entity.MiraiMessageTransmittable.Factory.sendTo
import com.xbaimiao.mirai.entity.enums.Permission
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.packet.enums.MessageType
import com.xbaimiao.mirai.packet.impl.group.*
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
    /**
     * 群聊
     */
    @SerializedName("group")
    val group: Group,
    /**
     * 群荣誉
     */
    @SerializedName("active")
    val active: MemberActive
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

    /**
     * 移出群聊
     * @param block 移除后拉黑
     * @param msg 踢出信息
     */
    fun kick(block: Boolean, msg: String): CompletableFuture<KickMemberFriendPacket.Result> {
        val future = CompletableFuture<KickMemberFriendPacket.Result>()
        KickMemberFriendPacket(id, group.id, block, msg).send().thenAcceptAsync {
            future.complete(it.result)
        }
        return future
    }

    /**
     * 移出群聊
     */
    fun kick(): CompletableFuture<KickMemberFriendPacket.Result> {
        return kick(false, "您已被移出群聊")
    }

    fun memberprofile(): CompletableFuture<QQProfile?> {
        val future = CompletableFuture<QQProfile?>()
        MemberProfilePacket(group.id, id).send().thenApplyAsync {
            future.complete(it.memberProfile)
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
