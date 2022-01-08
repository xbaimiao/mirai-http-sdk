package com.xbaimiao.mirai.entity.group

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.entity.MiraiMessageTransmittable
import com.xbaimiao.mirai.message.MiraiJsonSerializer
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.message.component.TextComponent
import com.xbaimiao.mirai.packet.impl.group.GroupMessagePacket

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
    val id: Long,
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
) : MiraiMessageTransmittable {

    override fun reply(message: Component) {
        println(GroupMessagePacket(MiraiJsonSerializer(this, message).serializer()).toJson())
        GroupMessagePacket(MiraiJsonSerializer(this, message).serializer()).sendAsync {
            println(this.body())
        }
    }

    override fun reply(message: String) {
        GroupMessagePacket(MiraiJsonSerializer(this, TextComponent(message)).serializer()).sendAsync()
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