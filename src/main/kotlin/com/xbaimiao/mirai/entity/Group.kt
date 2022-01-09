package com.xbaimiao.mirai.entity

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.entity.MiraiMessageTransmittable.Factory.sendTo
import com.xbaimiao.mirai.entity.enums.Permission
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.packet.enums.MessageType
import com.xbaimiao.mirai.packet.impl.group.EntityMembersPacket
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
) : MiraiMessageTransmittable, MiraiNumberIdentifiable<Group> {

    companion object Factory : MiraiNumberQueryable<Group> {
        override fun fromId(id: Long): Group {
            TODO("Not yet implemented")
        }

        override fun fromIdOrNull(id: Long): Group? {
            TODO("Not yet implemented")
        }
    }

    fun getMembers(): CompletableFuture<List<MemberFriend>> {
        return CompletableFuture<List<MemberFriend>>().apply {
            EntityMembersPacket(this@Group).send().thenAcceptAsync {
                this.complete(it.friends)
            }
        }
    }

    override fun sendMessage(component: BaseComponent): CompletableFuture<Message> {
        return component.sendTo(this, MessageType.GROUP)
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

}