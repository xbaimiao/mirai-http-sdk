package com.xbaimiao.mirai.entity

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.entity.MiraiMessageTransmittable.Factory.sendTo
import com.xbaimiao.mirai.entity.enums.Permission
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.packet.enums.MessageType
import com.xbaimiao.mirai.packet.impl.group.EntityMembersPacket
import com.xbaimiao.mirai.packet.impl.group.GetMemberInfoPacket
import java.util.concurrent.CompletableFuture

class Group(
    /**
     * 群名称
     */
    @SerializedName("name")
    val name: String,
    /**
     * 群号
     */
    @SerializedName("id")
    override val id: Long,
    /**
     * 机器人在此群的权限
     */
    @SerializedName("permission")
    val permission: Permission
) : MiraiMessageTransmittable {

    fun getMembers(): CompletableFuture<List<MemberFriend>> {
        return CompletableFuture<List<MemberFriend>>().apply {
            EntityMembersPacket(this@Group).send().thenAcceptAsync {
                this.complete(it.friends)
            }
        }
    }

    fun getMember(memberId: Long): CompletableFuture<MemberFriend?> {
        val future = CompletableFuture<MemberFriend?>()
        GetMemberInfoPacket(this.id, memberId).send().thenAcceptAsync {
            future.complete(it.memberFriend)
        }
        return future
    }

    override fun sendMessage(component: BaseComponent): CompletableFuture<Message> {
        return component.sendTo(this, MessageType.GROUP)
    }

    override fun quoteMessage(component: BaseComponent, quote: String): CompletableFuture<Message> {
        return component.sendTo(this, MessageType.GROUP, quote)
    }

    fun sendMessage(string: String): CompletableFuture<Message> {
        return sendMessage(Component.text(string))
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
