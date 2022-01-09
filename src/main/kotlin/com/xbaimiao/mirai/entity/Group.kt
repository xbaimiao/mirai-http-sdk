package com.xbaimiao.mirai.entity

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.impl.MessageImpl
import com.xbaimiao.mirai.message.serialize.MessageSerializer
import com.xbaimiao.mirai.packet.SyncIdPool
import com.xbaimiao.mirai.packet.enums.MessageType
import com.xbaimiao.mirai.packet.impl.group.MessagePacket

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
    val botPermission: BotPermission,

    /**
     * 会话Key
     */
    @SerializedName("sessionKey")
    internal val sessionKey: String

) : MiraiMessageTransmittable, MiraiNumberIdentifiable<Group> {
    override val type: MessageType = MessageType.GROUP

    companion object Factory : MiraiNumberQueryable<Group> {
        override fun fromId(id: Long): Group {
            TODO("Not yet implemented")
        }

        override fun fromIdOrNull(id: Long): Group? {
            TODO("Not yet implemented")
        }
    }

    override fun sendMessage(message: BaseComponent) {
        println("发送消息")
        val jsonObject = MessageSerializer.Json.serialize(MessageImpl(this, message))
        println(jsonObject.toString())
//        val packet = MessagePacket(jsonObject, type)
        println(SyncIdPool.next())
        println(jsonObject)
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

    enum class BotPermission {
        /**
         * 成员
         */
        MEMBER,

        /**
         * 群主
         */
        OWNER,

        /**
         * 管理员
         */
        ADMINISTRATOR
    }

}